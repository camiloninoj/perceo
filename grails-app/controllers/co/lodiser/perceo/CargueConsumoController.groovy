package co.lodiser.perceo

import grails.validation.ValidationException

import java.text.SimpleDateFormat
import java.text.NumberFormat

import static org.springframework.http.HttpStatus.*

class CargueConsumoController {

    CargueConsumoService cargueConsumoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cargueConsumoService.list(params), model:[cargueConsumoCount: cargueConsumoService.count()]
    }

    def show(Long id) {
        respond cargueConsumoService.get(id)
    }

    def create() {
        respond new CargueConsumo(params)
    }

    def save(CargueConsumo cargueConsumo) {
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

                reader.eachLine { tokens  ->
                    cargueConsumo.addToConsumos(new Consumo (
                            fecha: new SimpleDateFormat('dd/MM/yyyy').parse(tokens[0]),
                            vehiculo: Vehiculo.findByPlacaCivil(tokens[1]),
                            tipoCombustible: tokens[2],
                            cantidad: NumberFormat.getInstance().parse(tokens[3]),
                            importeTT: NumberFormat.getInstance().parse(tokens[4]),
                            numeroVenta: tokens[5],
                            eds: tokens[6],
                            puntoMedidaConsumo: NumberFormat.getInstance().parse(tokens[7]),
                            puntoMedidaKm: NumberFormat.getInstance().parse(tokens[8]),
                            codigoSap: tokens[9],
                            cargue:cargueConsumo)
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
}
