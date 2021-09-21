package co.lodiser.perceo

class Cliente {

    String unidadSigla
    String unidadNombre

    String divisionSigla
    String divisionNombre

    String brigadaSigla
    String brigadaNombre

    String ciudad

    String gestorFlotaSigla
    String gestorFlotaNombre

    String oficialS4Sigla
    String oficialS4Nombre

    String ejecutivoSigla
    String ejecutivoNombre

    static hasMany = [vehiculos: Vehiculo, destinos:Destino]

    static constraints = {
        unidadSigla blank: false, nullable: false, unique: true
        unidadNombre blank: false, nullable: false
        divisionSigla blank: false, nullable: false
        divisionNombre blank: false, nullable: false
        brigadaSigla blank: false, nullable: false
        brigadaNombre blank: false, nullable: false
        ciudad blank: false, nullable: false
        gestorFlotaSigla blank: false, nullable: false
        gestorFlotaNombre blank: false, nullable: false
        oficialS4Sigla blank: false, nullable: false
        oficialS4Nombre blank: false, nullable: false
        ejecutivoSigla blank: false, nullable: false
        ejecutivoNombre blank: false, nullable: false
    }

    String toString(){
        return unidadNombre
    }
}
