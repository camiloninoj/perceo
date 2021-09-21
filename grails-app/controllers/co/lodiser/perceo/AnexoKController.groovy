package co.lodiser.perceo

class AnexoKController {

    // Export service provided by Export plugin
    def exportService

    def index() {
    }

    def encontrarVehiculos = {
        render g.select(id:'vehiculo', name:'vehiculo.id',
                from:Cliente.get(params.idCliente).vehiculos, optionKey:'id', noSelection:[null:' '])
    }
}
