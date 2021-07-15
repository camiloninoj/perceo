package co.lodiser.perceo

class ReporteDetallado {

    String fecha
    String placaCivil
    String tipoVehiculo
    String tipoCombustible
    String cantidad
    String vlrUnit
    String importeTT
    String numeroVenta
    String ano
    String mes
    String mesNombre
    String diaMes
    String eds
    String placaMilitar
    String fechaCorrida
    String puntoMedidaConsumo
    String puntoMedidaKm
    String sigla
    String tasa
    String glBonos
    String gl
    String km
    String textoCombusitbleGl
    String textoCombusitbleBono
    String textoConsumoG
    String textoKilometrajeRecorrido
    String textoPlantillaAlmacen
    String unidadSigla
    String unidadNombre
    String divisionSigla
    String divisionNombre
    String brigadaSigla
    String brigadaNombre
    String gestorFlota
    String oficialS4
    String ejecutivo
    String codigoSap
    String centroCostos
    String equipo

    Consumo consumo

    static constraints = {
        fecha nullable:false, blank: false
        placaCivil nullable:false, blank: false
        tipoVehiculo nullable:false, blank: false
        tipoCombustible nullable:false, blank: false
        cantidad nullable:false, blank: false
        vlrUnit nullable:false, blank: false
        importeTT nullable:false, blank: false
        numeroVenta nullable:false, blank: false
        ano nullable:false, blank: false
        mes nullable:false, blank: false
        mesNombre nullable:false, blank: false
        diaMes nullable:false, blank: false
        eds nullable:false, blank: false
        placaMilitar nullable:false, blank: false
        fechaCorrida nullable:false, blank: false
        puntoMedidaConsumo nullable:true, blank: false
        puntoMedidaKm nullable:true, blank: false
        sigla nullable:false, blank: false
        tasa nullable:false, blank: false
        glBonos nullable:false, blank: false
        gl nullable:false, blank: false
        km nullable:false, blank: false
        textoCombusitbleGl nullable:false, blank: false
        textoCombusitbleBono nullable:false, blank: false
        textoConsumoG nullable:false, blank: false
        textoKilometrajeRecorrido nullable:false, blank: false
        textoPlantillaAlmacen nullable:false, blank: false
        unidadSigla nullable:false, blank: false
        unidadNombre nullable:false, blank: false
        divisionSigla nullable:false, blank: false
        divisionNombre nullable:false, blank: false
        brigadaSigla nullable:false, blank: false
        brigadaNombre nullable:false, blank: false
        gestorFlota nullable:false, blank: false
        oficialS4 nullable:false, blank: false
        ejecutivo nullable:false, blank: false
        codigoSap nullable:false, blank: false
        centroCostos nullable:false, blank: false
        equipo nullable:false, blank: false
    }

    String toString(){
        return placaMilitar+":"+fecha+"-"+cantidad
    }
}
