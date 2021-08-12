package co.lodiser.perceo

import grails.validation.ValidationException

import java.text.NumberFormat

import static org.springframework.http.HttpStatus.*

class CargueDestinoController {

    CargueDestinoService cargueDestinoService

    def assetResourceLocator

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cargueDestinoService.list(params), model:[cargueDestinoCount: cargueDestinoService.count()]
    }

    def show(Long id) {
        respond cargueDestinoService.get(id)
    }

    def create() {
        respond new CargueDestino(params)
    }

    def save(CargueDestino cargueDestino) {
        if (cargueDestino == null) {
            notFound()
            return
        }

        try {
            Destino.withTransaction{
                def archivo = request.getFile('archivo')
                cargueDestino.nombreArchivo = archivo.originalFilename

                def reader = archivo.inputStream.
                        toCsvReader([charset:'UTF-8',skipLines:1,separatorChar:';',batchSize:50])

                reader.eachLine { tokens ->
                    cargueDestino.addToDestinos(new Destino(
                            nombre: tokens[0],
                            distancia: NumberFormat.getInstance().parse(tokens[1]),
                            tiempoEstimado: tokens[2],
                            cliente: Cliente.findByUnidadSigla(tokens[3]))
                    )
                }

                cargueDestinoService.save(cargueDestino)
            }
        } catch (ValidationException e) {
            respond cargueDestino.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cargueDestino.label', default: 'CargueDestino'), cargueDestino.id])
                redirect cargueDestino
            }
            '*' { respond cargueDestino, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond cargueDestinoService.get(id)
    }

    def update(CargueDestino cargueDestino) {
        if (cargueDestino == null) {
            notFound()
            return
        }

        try {
            cargueDestinoService.save(cargueDestino)
        } catch (ValidationException e) {
            respond cargueDestino.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cargueDestino.label', default: 'CargueDestino'), cargueDestino.id])
                redirect cargueDestino
            }
            '*'{ respond cargueDestino, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cargueDestinoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cargueDestino.label', default: 'CargueDestino'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cargueDestino.label', default: 'CargueDestino'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def downloadArchivo(Long id){
        def cargue = cargueDestinoService.get(id)
        response.contentType = "application/CSV"
        response.setHeader("Content-Disposition","attachment;filename="+cargue.nombreArchivo)
        response.outputStream << cargue.archivo
        response.outputStream.flush()
    }

    def downloadFormato(){
        String fileName = "ejemplo_cargue_destinos.csv"
        def resource = assetResourceLocator.findResourceForURI("/downloadable/$fileName")

        response.setHeader("Content-Disposition","attachment;filename=${resource.filename}")
        response.contentType = 'text/csv'
        response.outputStream << resource.inputStream.bytes
    }
}
