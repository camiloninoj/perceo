package co.lodiser.perceo

class CargueVehiculo {

    String nombreArchivo
    Date fechaCargue = new Date()
    byte[] archivo

    static hasMany = [vehiculos: Vehiculo]

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
