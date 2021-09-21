package co.lodiser.perceo

import java.text.SimpleDateFormat

class Consumo {

    Date fecha
    Vehiculo vehiculo
    String tipoCombustible
    Double vlrUnit
    Double cantidad
    Double importeTT
    String numeroVenta
    String eds
    String codigoSap
    CargueConsumo cargue

    static hasOne = [reporte:ReporteDetallado]
    static belongsTo = [vehiculo: Vehiculo]
    static hasMany = [rutas:Ruta]

    def afterInsert() {
        reporte = new ReporteDetallado()
        reporte.consumo = this
        reporte.fecha = new SimpleDateFormat('dd/MM/yyyy').format(fecha)
        reporte.placaCivil = vehiculo.placaCivil
        reporte.tipoVehiculo = vehiculo.tasaFalla.tipoVehiculo
        reporte.tipoCombustible = tipoCombustible
        reporte.cantidad = cantidad
        reporte.vlrUnit = vlrUnit
        reporte.importeTT = importeTT
        reporte.numeroVenta = numeroVenta
        reporte.ano = new SimpleDateFormat('yyyy').format(fecha)
        reporte.mes = new SimpleDateFormat('MM').format(fecha)
        reporte.mesNombre = new SimpleDateFormat('MMMM').format(fecha)
        reporte.diaMes = new SimpleDateFormat('dd').format(fecha)
        reporte.eds = eds
        reporte.placaMilitar = vehiculo.placaMilitar
        reporte.fechaCorrida = new SimpleDateFormat('ddMMyyyy').format(fecha)
        reporte.puntoMedidaConsumo = vehiculo.puntoMedidaConsumo
        reporte.puntoMedidaKm = vehiculo.puntoMedidaKm
        reporte.sigla = vehiculo.tasaFalla.sigla
        reporte.tasa = vehiculo.tasaFalla.tasa
        reporte.glBonos = cantidad
        reporte.gl = cantidad
        reporte.km = cantidad * vehiculo.tasaFalla.tasa
        reporte.textoCombusitbleGl = "CONSUMO DE COMBUSTIBLE "+reporte.fechaCorrida
        reporte.textoCombusitbleBono = "CONSUMO DE BONOS "+reporte.diaMes
        reporte.textoConsumoG = "CONSUMO_COMBUSTIBLE_G "+vehiculo.placaMilitar
        reporte.textoKilometrajeRecorrido = "KILOMETRAJE RECORRIDO "+vehiculo.placaMilitar
        reporte.textoPlantillaAlmacen = "CONSUMO "+reporte.mesNombre+" DIA "+reporte.diaMes
        reporte.unidadSigla = vehiculo.cliente.unidadSigla
        reporte.unidadNombre = vehiculo.cliente.unidadNombre
        reporte.divisionSigla = vehiculo.cliente.divisionSigla
        reporte.divisionNombre = vehiculo.cliente.divisionNombre
        reporte.brigadaSigla = vehiculo.cliente.brigadaSigla
        reporte.brigadaNombre = vehiculo.cliente.brigadaNombre
        reporte.gestorFlota = vehiculo.cliente.gestorFlotaSigla+" "+vehiculo.cliente.gestorFlotaNombre
        reporte.oficialS4 = vehiculo.cliente.oficialS4Sigla+" "+vehiculo.cliente.oficialS4Nombre
        reporte.ejecutivo = vehiculo.cliente.ejecutivoSigla+" "+vehiculo.cliente.ejecutivoNombre
        reporte.codigoSap = codigoSap
        reporte.centroCostos = vehiculo.centroCostos
        reporte.equipo = vehiculo.equipo
    }

    static constraints = {
        fecha nullable:false, max: new Date()
        tipoCombustible  nullable:false, blank:false, inList: ["DIESEL","CORRIENTE","EXTRA"]
        vlrUnit nullable:false, min:0D
        cantidad nullable:false, min: 0D
        importeTT nullale:false, min:0D
        numeroVenta nullable:false, blank: false
        eds nullable:false, blank: false
        codigoSap nullable:false, blank: false
        cargue nullable:true
        reporte nullable: true
    }

    String toString(){
        if (vehiculo)
            return vehiculo.placaCivil+"_"+fecha
    }
}
