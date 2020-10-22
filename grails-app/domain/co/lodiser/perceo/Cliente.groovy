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

    String oficialS4Unidad
    String oficialS4Nombre

    String ejecutivoUnidad
    String ejecutivoNombre

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
        oficialS4Unidad blank: false, nullable: false
        oficialS4Nombre blank: false, nullable: false
        ejecutivoUnidad blank: false, nullable: false
        ejecutivoNombre blank: false, nullable: false
    }

    String toString(){
        return unidadNombre
    }
}
