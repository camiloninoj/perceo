package co.lodiser.perceo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TasaFallaController {

    TasaFallaService tasaFallaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond tasaFallaService.list(params), model:[tasaFallaCount: tasaFallaService.count()]
    }

    def show(Long id) {
        respond tasaFallaService.get(id)
    }

    def create() {
        respond new TasaFalla(params)
    }

    def save(TasaFalla tasaFalla) {
        if (tasaFalla == null) {
            notFound()
            return
        }

        try {
            tasaFallaService.save(tasaFalla)
        } catch (ValidationException e) {
            respond tasaFalla.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tasaFalla.label', default: 'TasaFalla'), tasaFalla.id])
                redirect tasaFalla
            }
            '*' { respond tasaFalla, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tasaFallaService.get(id)
    }

    def update(TasaFalla tasaFalla) {
        if (tasaFalla == null) {
            notFound()
            return
        }

        try {
            tasaFallaService.save(tasaFalla)
        } catch (ValidationException e) {
            respond tasaFalla.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tasaFalla.label', default: 'TasaFalla'), tasaFalla.id])
                redirect tasaFalla
            }
            '*'{ respond tasaFalla, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        tasaFallaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tasaFalla.label', default: 'TasaFalla'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tasaFalla.label', default: 'TasaFalla'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
