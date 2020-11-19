package co.lodiser.perceo

import java.text.SimpleDateFormat

class Consumo {

    Date fecha
    Vehiculo vehiculo
    String tipoCombustible
    Double cantidad
    Double importeTT
    String numeroVenta
    String eds
    Integer puntoMedidaConsumo
    Integer puntoMedidaKm
    String codigoSap

    static hasOne = [reporte:ReporteDetallado]
    static belongsTo = [cargue: CargueConsumo]

    def beforeValidate() {
        reporte = new ReporteDetallado()
        reporte.consumo = this
        reporte.fecha = new SimpleDateFormat('dd/MM/yyyy').format(fecha)
        reporte.placaCivil = vehiculo.placaCivil
        reporte.tipoVehiculo = vehiculo.tasaFalla.tipoVehiculo
        reporte.tipoCombustible = tipoCombustible
        reporte.cantidad = cantidad
        switch (tipoCombustible){
            case "DIESEL":
                reporte.vlrUnit =  cargue.precioGlDiesel
            case "CORRIENTE":
                reporte.vlrUnit =  cargue.precioGlCorriente
            case "EXTRA":
                reporte.vlrUnit =  cargue.precioGlExtra
            default:
                reporte.vlrUnit =  cargue.precioGlCorriente
        }

        reporte.importeTT = importeTT
        reporte.numeroVenta = numeroVenta
        reporte.diaMes = new SimpleDateFormat('dd').format(fecha)
        reporte.eds = eds
        reporte.placaMilitar = vehiculo.placaMilitar
        reporte.fechaCorrida = new SimpleDateFormat('ddMMyyyy').format(fecha)
        reporte.puntoMedidaConsumo = puntoMedidaConsumo
        reporte.puntoMedidaKm = puntoMedidaConsumo
        reporte.sigla = vehiculo.tasaFalla.sigla
        reporte.tasa = vehiculo.tasaFalla.tasa
        reporte.glBonos = cantidad
        reporte.gl = cantidad
        reporte.km = cantidad * vehiculo.tasaFalla.tasa
        reporte.textoCombusitbleGl = "CONSUMO DE COMBUSTIBLE "+reporte.fechaCorrida
        reporte.textoCombusitbleBono = "CONSUMO DE BONOS "+reporte.diaMes
        reporte.textoConsumoG = "CONSUMO_COMBUSTIBLE_G "+vehiculo.placaMilitar
        reporte.textoKilometrajeRecorrido = "KILOMETRAJE RECORRIDO "+vehiculo.placaMilitar
        reporte.textoPlantillaAlmacen = "CONSUMO "+cargue.mes+" DIA "+reporte.diaMes
        reporte.unidadSigla = vehiculo.cliente.unidadSigla
        reporte.divisionSigla = vehiculo.cliente.divisionSigla
        reporte.brigadaSigla = vehiculo.cliente.brigadaSigla
        reporte.codigoSap = codigoSap
        reporte.centroCostos = vehiculo.centroCostos
        reporte.equipo = vehiculo.equipo
        reporte
    }

    static constraints = {
        fecha nullable:false, max: new Date()
        vehiculo nullable:false
        tipoCombustible  nullable:false, blank:false, inList: ["DIESEL","CORRIENTE","EXTRA"]
        cantidad nullable:false, min: 0D
        importeTT nullale:false, min:0D
        numeroVenta nullable:false, blank: false
        eds nullable:false, blank: false
        puntoMedidaConsumo nullable:false, min:0
        puntoMedidaKm nullable:false, min:0
        codigoSap nullable:false, blank: false
    }

    String toString(){
        return vehiculo.placaCivil+"_"+fecha
    }
}
