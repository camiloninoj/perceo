package co.lodiser.perceo

class Ruta {

    Destino destino
    Date fecha
    Double kmInicial
    Double kmFinal
    Ruta anterior

    static belongsTo = [consumo: Consumo]

    static constraints = {
        destino nullable: false
        fecha nullable:false, max: new Date()
        kmInicial nullable: false, min: 0D
        kmFinal nullable:false, min: 0D
        anterior nullable:true
    }
}
