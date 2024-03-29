package co.lodiser.perceo

import grails.validation.ValidationException

import java.text.SimpleDateFormat
import java.text.NumberFormat

import static org.springframework.http.HttpStatus.*

class CargueConsumoController {

    CargueConsumoService cargueConsumoService

    def assetResourceLocator

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def cliente = authenticatedUser.cliente
        if (cliente){
            respond CargueConsumo.findAllByCliente(cliente,params),
                    model:[clienteCount: CargueConsumo.countByCliente(cliente)]
            return
        }
        respond cargueConsumoService.list(params), model:[cargueConsumoCount: cargueConsumoService.count()]
    }

    def show(Long id) {
        respond cargueConsumoService.get(id)
    }

    def create() {
        respond new CargueConsumo(params), model:[cliente:authenticatedUser.cliente]
    }

    def save(CargueConsumo cargueConsumo) {
        def clienteAuth = authenticatedUser.cliente
        if (cargueConsumo == null) {
            notFound()
            return
        }

        try {
           Consumo.withTransaction{
               def archivo = request.getFile('archivo')
               cargueConsumo.nombreArchivo = archivo.originalFilename

               def reader = archivo.inputStream.
                       toCsvReader([charset:'UTF-8',skipLines:1,separatorChar:';',batchSize:50])

                reader.eachLine { tokens ->
                    cargueConsumo.addToConsumos(new Consumo(
                            fecha: new SimpleDateFormat('dd/MM/yyyy').parse(tokens[0]),
                            vehiculo: clienteAuth?Vehiculo.findByPlacaCivilAndCliente(tokens[1],clienteAuth):Vehiculo.findByPlacaCivil(tokens[1]),
                            tipoCombustible: tokens[2],
                            vlrUnit: tokens[3],
                            cantidad: NumberFormat.getInstance().parse(tokens[4]),
                            importeTT: NumberFormat.getInstance().parse(tokens[5]),
                            numeroVenta: tokens[6],
                            eds: tokens[7],
                            codigoSap: tokens[8])
                    )
                }

               cargueConsumoService.save(cargueConsumo)
            }
        } catch (ValidationException e) {
            respond cargueConsumo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cargueConsumo.label', default: 'CargueConsumo'), cargueConsumo.id])
                redirect cargueConsumo
            }
            '*' { respond cargueConsumo, [status: CREATED] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cargueConsumoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cargueConsumo.label', default: 'CargueConsumo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cargueConsumo.label', default: 'CargueConsumo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def downloadArchivo(Long id){
        def cargue = cargueConsumoService.get(id)
        response.contentType = "application/CSV"
        response.setHeader("Content-Disposition","attachment;filename="+cargue.nombreArchivo)
        response.outputStream << cargue.archivo
        response.outputStream.flush()
    }

    def downloadFormato(){
        String fileName = "ejemplo_cargue_consumos.csv"
        def resource = assetResourceLocator.findResourceForURI("/downloadable/$fileName")

        response.setHeader("Content-Disposition","attachment;filename=${resource.filename}")
        response.contentType = 'text/csv'
        response.outputStream << resource.inputStream.bytes
    }
}
