package co.lodiser.perceo

class DashboardController {

    def index() {
        [consXtipoCombusData:consXtipoCombusData()]
        [distCombustibleData:distCombustibleData()]
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

    def distCombustibleData() {
        def clientId = 1L
        def cal = Calendar.getInstance()
        def today = cal.getTime()
        cal.add(Calendar.YEAR, -2)
        def monthBehind = cal.getTime()

        return Consumo.executeQuery("select c.tipoCombustible, sum(c.importeTT) " +
                "from Consumo c " +
                "where c.vehiculo.cliente.id = :clientId " +
                "and fecha between :to and :from " +
                "group by tipoCombustible ",
                [clientId: clientId, to: monthBehind, from: today])
    }
}
