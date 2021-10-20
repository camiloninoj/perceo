package co.lodiser.perceo

class AnexoKController {

    // Export service provided by Export plugin
    def exportService
    def tablasAbordoService

    def index() {
    }

    def encontrarVehiculos = {
        render g.select(id:'vehiculo', name:'vehiculo.id',
                from:Cliente.get(params.idCliente).vehiculos, optionKey:'id', noSelection:[null:' '])
    }

    def lanzarJob(){
        tablasAbordoService.buscarClientes()
        render "Lanzado"
    }
}
