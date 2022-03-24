package co.lodiser.perceo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DestinoController {

    DestinoService destinoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def cliente = authenticatedUser.cliente
        if (cliente){
            respond Destino.findAllByCliente(cliente,params), model:[clienteCount: Destino.countByCliente(cliente)]
            return
        }
        respond destinoService.list(params), model:[destinoCount: destinoService.count()]
    }

    def show(Long id) {
        respond destinoService.get(id)
    }

    def create() {
        respond new Destino(params), model:[cliente:authenticatedUser.cliente]
    }

    def save(Destino destino) {
        if (destino == null) {
            notFound()
            return
        }

        try {
            destinoService.save(destino)
        } catch (ValidationException e) {
            respond destino.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'destino.label', default: 'Destino'), destino.id])
                redirect destino
            }
            '*' { respond destino, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond destinoService.get(id), model:[cliente:authenticatedUser.cliente]
    }

    def update(Destino destino) {
        if (destino == null) {
            notFound()
            return
        }

        try {
            destinoService.save(destino)
        } catch (ValidationException e) {
            respond destino.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'destino.label', default: 'Destino'), destino.id])
                redirect destino
            }
            '*'{ respond destino, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        destinoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'destino.label', default: 'Destino'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'destino.label', default: 'Destino'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
