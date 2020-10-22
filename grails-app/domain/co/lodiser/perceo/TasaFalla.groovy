package co.lodiser.perceo

class TasaFalla {

    String sigla
    String tipoVehiculo
    Integer tasa

    static constraints = {
        sigla blank: false, nullable: false, size: 1..2, unique: true
        tipoVehiculo blank: false, nullable: false
        tasa nullable: false, size: 0..100
    }

    String toString(){
        return sigla
    }
}
