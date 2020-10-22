package co.lodiser.perceo

class Vehiculo {

    String placaMilitar
    String placaCivil
    String centroCostos
    String equipo
    TasaFalla tasaFalla

    static belongsTo = [cliente: Cliente]

    def beforeInsert() {
        tasaFalla = TasaFalla.findBySigla(placaMilitar.charAt(0))
    }

    static constraints = {
        placaMilitar blank: false, nullable: false, unique: true, size: 6..6
        placaCivil blank: false, nullable: false, unique: true, size: 6..6
        centroCostos blank: false, nullable: false
        equipo blank: false, nullable: false
        tasaFalla blank: false, nullable: false
    }

    String toString(){
        return placaMilitar
    }
}
