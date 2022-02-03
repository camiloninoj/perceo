package co.lodiser.perceo

class DashboardController {

    def index() {
        def clienteId = 1l //Con seguridad, debe salir del usuario
        def cal = Calendar.getInstance()

        def hoy = cal.getTime()
        cal.add(Calendar.YEAR,-2) //En producción, principio de mes
        def mesAtras = cal.getTime()

        [consXtipoCombusData:consXtipoCombusData(clienteId,mesAtras,hoy),
         conXedsData:conXedsData(clienteId,mesAtras,hoy),
         consXvehiculoData:consXvehiculoData(clienteId,mesAtras,hoy),
         consXmesData:consXmesData(clienteId,mesAtras,hoy),
         distCombustibleData:distCombustibleData(clienteId,mesAtras,hoy),
         consumoXTipoVehiculoData:consumoXTipoVehiculoData(clienteId,mesAtras,hoy)
        ]
    }

    def consXtipoCombusData(def clienteId, def inicio, def fin){
        return Consumo.executeQuery("select tipoCombustible, sum(cantidad) " +
                "from Consumo c " +
                "where c.vehiculo.cliente.id = :clienteId " +
                "and fecha between :fechaInicio and :fechaFin " +
                "group by tipoCombustible ",
                [clienteId:clienteId,fechaInicio:inicio,fechaFin:fin])
    }

    def conXedsData(def clienteId, def inicio, def fin){
        return Consumo.executeQuery("select eds, sum(cantidad) " +
                "from Consumo c " +
                "where c.vehiculo.cliente.id = :clienteId " +
                "and fecha between :fechaInicio and :fechaFin " +
                "group by tipoCombustible ",
                [clienteId:clienteId,fechaInicio:inicio,fechaFin:fin])
    }

    def consXvehiculoData(def clienteId, def inicio, def fin){
        return Consumo.executeQuery("select c.vehiculo.placaCivil, " +
                "sum(case when tipoCombustible='DIESEL' then cantidad else 0 end) as consumo_diesel, " +
                "sum(case when tipoCombustible='CORRIENTE' then cantidad else 0 end) as consumo_corriente, " +
                "sum(case when tipoCombustible='EXTRA' then cantidad else 0 end) as consumo_extra  " +
                "from Consumo c " +
                "where c.vehiculo.cliente.id = :clienteId " +
                "and fecha between :fechaInicio and :fechaFin ",
                [clienteId:clienteId,fechaInicio:inicio,fechaFin:fin])
    }

    def consXmesData(def clienteId, def inicio, def fin){
        def consXmesData = Consumo.executeQuery("select diaMes, sum(cantidad) " +
                "from ReporteDetallado rd " +
                "where rd.consumo.vehiculo.cliente.id = :clienteId " +
                "and rd.consumo.fecha between :fechaInicio and :fechaFin " +
                "group by diaMes ",
                [clienteId:clienteId,fechaInicio:inicio,fechaFin:fin])

        def mesCompleto = [["01",0.0],["02",0.0],["03",0.0],["04",0.0],["05",0.0],["06",0.0],["07",0.0],["08",0.0],["09",0.0],["10",0.0],
                           ["11",0.0],["12",0.0],["13",0.0],["14",0.0],["15",0.0],["16",0.0],["17",0.0],["18",0.0],["19",0.0],["20",0.0],
                           ["21",0.0],["22",0.0],["23",0.0],["24",0.0],["25",0.0],["26",0.0],["27",0.0],["28",0.0],["29",0.0],["30",0.0],["31",0.0]]
        def dia
        def index

        for (i in mesCompleto){//31 dias de mes
            dia = consXmesData.find{ it[0].equals(i[0])}
            if (dia){//Si se encuentra el d{ia en el resultado, agregarlo al arreglo
                index = mesCompleto.indexOf(i)
                mesCompleto.putAt(index, dia)
            }
        }

        return mesCompleto
    }

    def distCombustibleData(def clienteId, def inicio, def fin) {
        return Consumo.executeQuery("select c.tipoCombustible, sum(c.importeTT) " +
                "from Consumo c " +
                "where c.vehiculo.cliente.id = :clientId " +
                "and fecha between :to and :from " +
                "group by tipoCombustible ",
                [clientId: clienteId, to: inicio, from: fin])
    }

    def consumoXTipoVehiculoData(def clienteId, def inicio, def fin) {
        return Consumo.executeQuery("select t.tipoVehiculo, sum(c.cantidad) " +
                "from Consumo c, TasaFalla t " +
                "where c.vehiculo.cliente.id = :clienteId " +
                "and c.vehiculo.tasaFalla = t " +
                "and fecha between :to and :from " +
                "group by t.tipoVehiculo ",
                [clienteId: clienteId, to: inicio, from: fin])

    }
}
