package co.lodiser.perceo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ConsumoController {

    ConsumoService consumoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def cliente = authenticatedUser.cliente
        if (cliente){
            respond Consumo.findAllByVehiculoInList(cliente.vehiculos,params),
                    model:[clienteCount: Consumo.countByVehiculoInList(cliente.vehiculos)]
            return
        }
        respond consumoService.list(params), model:[detalleConsumoCount: consumoService.count()]
    }

    def show(Long id) {
        respond consumoService.get(id)
    }

    def create() {
        respond new Consumo(params), model:[vehiculos: authenticatedUser.cliente?.vehiculos]
    }

    def save(Consumo consumo) {
        if (consumo == null) {
            notFound()
            return
        }

        try {
            consumoService.save(consumo)
        } catch (ValidationException e) {
            respond consumo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consumo.label', default: 'Consumo'), consumo.id])
                redirect consumo
            }
            '*' { respond consumo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond consumoService.get(id), model:[vehiculos: authenticatedUser.cliente?.vehiculos]
    }

    def update(Consumo consumo) {
        if (consumo == null) {
            notFound()
            return
        }

        try {
            consumoService.save(consumo)
        } catch (ValidationException e) {
            respond consumo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'consumo.label', default: 'Consumo'), consumo.id])
                redirect consumo
            }
            '*'{ respond consumo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        consumoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'consumo.label', default: 'Consumo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consumo.label', default: 'Consumo'), params.id])
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
}
