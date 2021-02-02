package co.lodiser.perceo

class CargueConsumo {

    String nombreArchivo
    Date fechaCargue = new Date()
    byte[] archivo

    static hasMany = [consumos: Consumo]

    static constraints = {
        nombreArchivo nullable:false, blank:false, validator:{
            if (!it.endsWith('.csv')) return ['noCSV']
        }
        fechaCargue nullable:false
        archivo nullable:false, maxSize: 1024 * 1024 * 2
    }

    String toString(){
        return nombreArchivo
    }
}
