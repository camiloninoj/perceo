package co.lodiser.perceo

import java.text.SimpleDateFormat

class ReporteDetalladoController {

    // Export service provided by Export plugin
    def exportService

    ReporteDetalladoService reporteDetalladoService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        if(params?.f && params.f != "html"){
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=reporte-detallado.${params.extension}")

            def fields = ["fecha",
                          "placaCivil",
                          "tipoVehiculo",
                          "tipoCombustible",
                          "cantidad",
                          "vlrUnit",
                          "importeTT",
                          "numeroVenta",
                          "diaMes",
                          "eds",
                          "placaMilitar",
                          "fechaCorrida",
                          "puntoMedidaConsumo",
                          "puntoMedidaKm",
                          "sigla",
                          "tasa",
                          "glBonos",
                          "gl",
                          "km",
                          "textoCombusitbleGl",
                          "textoCombusitbleBono",
                          "textoConsumoG",
                          "textoKilometrajeRecorrido",
                          "textoPlantillaAlmacen",
                          "unidadSigla",
                          "divisionSigla",
                          "brigadaSigla",
                          "codigoSap",
                          "centroCostos",
                          "equipo"]
            def labels = ["fecha": "FECHA",
                          "placaCivil": "PLACA CIVIL",
                          "tipoVehiculo":"TIPO VEHICULO",
                          "tipoCombustible":"TIPO COMBUSTIBLE",
                          "cantidad":"CANT",
                          "vlrUnit":"VLR UNIT",
                          "importeTT":"IMPORTE TT",
                          "numeroVenta":"SERIES / No. VENTA",
                          "diaMes":"DIA MES",
                          "eds":"EDS",
                          "placaMilitar":"PLACA MILITAR",
                          "fechaCorrida": "FECHA CORRIDA",
                          "puntoMedidaConsumo": "PTO MED COMB",
                          "puntoMedidaKm": "PTO MED KIL",
                          "sigla": "SIGLA",
                          "tasa": "TASA FALLA",
                          "glBonos": "GALONAJE BONOS",
                          "gl": "GL",
                          "km":"KM",
                          "textoCombusitbleGl":"TEXTO BREVE COMBUSTIBLE EN GL",
                          "textoCombusitbleBono": "TEXTO BREVE COMBUSTIBLE EN BONOS",
                          "textoConsumoG": "CONSUMO_COMBUSTIBLE_G",
                          "textoKilometrajeRecorrido": "KILOMETRAJE RECORRIDO",
                          "textoPlantillaAlmacen": "TEXTO BREVE PLANTILLA ALMACEN",
                          "unidadSigla":"UNIDAD",
                          "divisionSigla":"DIVISION",
                          "brigadaSigla":"BRIGADA",
                          "codigoSap":"CODIGO SAP",
                          "centroCostos":"CENTRO COSTOS",
                          "equipo": "EQUIPO"]

            Map formatters = [:]
            Map parameters = [:]

            exportService.export(params.f, response.outputStream,ReporteDetallado.list(params), fields, labels, formatters, parameters)
        }

        respond ReporteDetallado.list(params), model:[reporteDetalladoCount: ReporteDetallado.count()]
    }

    def show(Long id) {
        respond reporteDetalladoService.get(id)
    }
}
