package co.lodiser.perceo

class TablasAbordoJob {

    def tablasAbordoService

    static triggers = {
        simple name: 'simpleTrigger', startDelay: 10000, repeatInterval: 43200000
    }

    def execute() {
        println "-----Iniciando tarea calculo de rutas: "+new Date()+"-----"
        tablasAbordoService.buscarClientes()
        println "-----Finalizando tarea calculo de rutas: "+new Date()+"-----"
    }
}
