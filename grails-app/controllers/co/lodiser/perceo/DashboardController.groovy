package co.lodiser.perceo

class DashboardController {

    def index() {
        [consXtipoCombusData:consXtipoCombusData()]
    }

    def consXtipoCombusData(){
        def clienteId = 1l //Con seguridad debe salir del usuario
        def cal = Calendar.getInstance()

        def hoy = cal.getTime()
        cal.add(Calendar.YEAR,-2) //En producción un mes atras
        def mesAtras = cal.getTime()

        return Consumo.executeQuery("select tipoCombustible, sum(cantidad) " +
                "from Consumo c " +
                "where c.vehiculo.cliente.id = :clienteId " +
                "and fecha between :fechaInicio and :fechaFin " +
                "group by tipoCombustible ",
                [clienteId:clienteId,fechaInicio:mesAtras,fechaFin:hoy])
    }
}
