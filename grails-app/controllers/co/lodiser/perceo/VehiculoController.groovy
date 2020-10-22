package co.lodiser.perceo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class VehiculoController {

    VehiculoService vehiculoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vehiculoService.list(params), model:[vehiculoCount: vehiculoService.count()]
    }

    def show(Long id) {
        respond vehiculoService.get(id)
    }

    def create() {
        respond new Vehiculo(params)
    }

    def save(Vehiculo vehiculo) {
        if (vehiculo == null) {
            notFound()
            return
        }

        try {
            vehiculoService.save(vehiculo)
        } catch (ValidationException e) {
            respond vehiculo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), vehiculo.id])
                redirect vehiculo
            }
            '*' { respond vehiculo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vehiculoService.get(id)
    }

    def update(Vehiculo vehiculo) {
        if (vehiculo == null) {
            notFound()
            return
        }

        try {
            vehiculoService.save(vehiculo)
        } catch (ValidationException e) {
            respond vehiculo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), vehiculo.id])
                redirect vehiculo
            }
            '*'{ respond vehiculo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vehiculoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehiculo.label', default: 'Vehiculo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
