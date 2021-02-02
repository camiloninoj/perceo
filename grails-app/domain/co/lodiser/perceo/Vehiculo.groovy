package co.lodiser.perceo

class Vehiculo {

    String placaMilitar
    String placaCivil
    String centroCostos
    String equipo
    TasaFalla tasaFalla
    Integer puntoMedidaConsumo
    Integer puntoMedidaKm
    CargueVehiculo cargue

    static belongsTo = [cliente: Cliente]
    static hasMany = [consumos: Consumo]

    def beforeInsert() {
        tasaFalla = TasaFalla.findBySigla(placaMilitar.charAt(0))
    }

    static constraints = {
        placaMilitar blank: false, nullable: false, unique: true, size: 6..6
        placaCivil blank: false, nullable: false, unique: true, size: 6..6
        centroCostos blank: false, nullable: false
        equipo blank: false, nullable: false
        tasaFalla blank: false, nullable: false
        puntoMedidaConsumo nullable:true, min:0
        puntoMedidaKm nullable:true, min:0
        cargue nullable:true
    }

    static mapping = {
        consumos cascade: 'all-delete-orphan'
    }

    String toString(){
        return placaMilitar
    }
}
