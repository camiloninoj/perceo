package co.lodiser.perceo

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class CargueVehiculoController {

    CargueVehiculoService cargueVehiculoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cargueVehiculoService.list(params), model:[cargueVehiculoCount: cargueVehiculoService.count()]
    }

    def show(Long id) {
        respond cargueVehiculoService.get(id)
    }

    def create() {
        respond new CargueVehiculo(params)
    }

    def save(CargueVehiculo cargueVehiculo) {
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
                    cargueVehiculo.addToVehiculos(new Vehiculo(
                            placaMilitar: tokens[0],
                            placaCivil: tokens[1],
                            centroCostos: tokens[2],
                            equipo: tokens[3],
                            cliente: Cliente.findByUnidadSigla(tokens[4]),
                            tasaFalla: TasaFalla.findBySigla(tokens[0].charAt(0)))
                    )
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
}
