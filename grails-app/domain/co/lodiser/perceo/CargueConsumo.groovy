package co.lodiser.perceo

class CargueConsumo {

    String nombreArchivo
    String mes
    Double precioGlDiesel
    Double precioGlCorriente
    Double precioGlExtra
    Date fechaCargue = new Date()
    byte[] archivo

    static hasMany = [consumos: Consumo]

    static constraints = {
        nombreArchivo nullable:false, blank:false, validator:{
            if (!it.endsWith('.csv')) return ['noCSV']
        }
        mes nullable:false, blank:false, inList: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",  "Septiembre", "Octubre", "Noviembre", "Diciembre"]
        precioGlDiesel nullable:false, min:0D
        precioGlCorriente nullable:false, min:0D
        precioGlExtra nullable:false, min:0D
        fechaCargue nullable:false
        archivo nullable:false, maxSize: 1024 * 1024 * 2
    }

    String toString(){
        return nombreArchivo
    }
}
