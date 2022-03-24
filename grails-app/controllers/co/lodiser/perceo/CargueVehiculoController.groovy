package co.lodiser.perceo

import grails.validation.ValidationException

import java.text.NumberFormat

import static org.springframework.http.HttpStatus.*

class CargueVehiculoController {

    def assetResourceLocator
    CargueVehiculoService cargueVehiculoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def cliente = authenticatedUser.cliente
        if (cliente){
            respond CargueVehiculo.findAllByCliente(cliente,params),
                    model:[clienteCount: CargueVehiculo.countByCliente(cliente),
                           cliente:cliente]
            return
        }
        respond cargueVehiculoService.list(params), model:[cargueVehiculoCount: cargueVehiculoService.count()]
    }

    def show(Long id) {
        respond cargueVehiculoService.get(id)
    }

    def create() {
        respond new CargueVehiculo(params), model:[cliente:authenticatedUser.cliente]
    }

    def save(CargueVehiculo cargueVehiculo) {
        def clienteAuth = authenticatedUser.cliente
        if (cargueVehiculo == null) {
            notFound()
            return
        }

        try {
            Vehiculo.withTransaction{
                def archivo = request.getFile('archivo')
                cargueVehiculo.nombreArchivo = archivo.originalFilename

                def reader = archivo.inputStream.
                        toCsvReader([charset:'UTF-8',skipLines:1,separatorChar:';',batchSize:50])

                reader.eachLine { tokens  ->
                    Vehiculo vehiculo = Vehiculo.findByPlacaMilitar(tokens[0])

                    if (!vehiculo){
                        vehiculo = new Vehiculo()
                    }
                    vehiculo.placaMilitar = tokens[0]
                    vehiculo.placaCivil = tokens[1]
                    vehiculo.centroCostos = tokens[2]
                    vehiculo.equipo = tokens[3]
                    vehiculo.cliente = clienteAuth?clienteAuth:Cliente.findByUnidadSigla(tokens[4])
                    vehiculo.tasaFalla = TasaFalla.findBySigla(tokens[0].charAt(0))
                    vehiculo.kmInicial = NumberFormat.getInstance().parse(tokens[5])

                    cargueVehiculo.addToVehiculos(vehiculo)
                }

                cargueVehiculoService.save(cargueVehiculo)
            }
        } catch (ValidationException e) {
            respond cargueVehiculo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cargueVehiculo.label', default: 'CargueVehiculo'), cargueVehiculo.id])
                redirect cargueVehiculo
            }
            '*' { respond cargueVehiculo, [status: CREATED] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cargueVehiculoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cargueVehiculo.label', default: 'CargueVehiculo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cargueVehiculo.label', default: 'CargueVehiculo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def downloadArchivo(Long id){
        def cargue = cargueVehiculoService.get(id)
        response.contentType = "application/CSV"
        response.setHeader("Content-Disposition","attachment;filename="+cargue.nombreArchivo)
        response.outputStream << cargue.archivo
        response.outputStream.flush()
    }

    def downloadFormato(){
        String fileName = "ejemplo_cargue_vehiculos.csv"
        def resource = assetResourceLocator.findResourceForURI("/downloadable/$fileName")

        response.setHeader("Content-Disposition","attachment;filename=${resource.filename}")
        response.contentType = 'text/csv'
        response.outputStream << resource.inputStream.bytes
    }
}
