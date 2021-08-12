package co.lodiser.perceo

class Destino {

    String nombre
    Double distancia
    Integer tiempoEstimado

    static belongsTo = [cliente: Cliente]

    static constraints = {
        nombre blank: false, nullable: false, unique: 'cliente'
        distancia nullable: false, min: 0D
        tiempoEstimado nullable: true, min: 0
    }

    String toString(){
        return nombre
    }
}