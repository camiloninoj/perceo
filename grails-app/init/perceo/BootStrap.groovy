package perceo

import co.lodiser.perceo.TasaFalla

class BootStrap {

    def init = { servletContext ->
        new TasaFalla(sigla:'A',tipoVehiculo:'Ambulancia',tasa:25).save()
        new TasaFalla(sigla:'B',tipoVehiculo:'Automovil',tasa:35).save()
        new TasaFalla(sigla:'C',tipoVehiculo:'Camioneta',tasa:25).save()
        new TasaFalla(sigla:'D',tipoVehiculo:'Campero',tasa:25).save()
        new TasaFalla(sigla:'F',tipoVehiculo:'Moto',tasa:60).save()
        new TasaFalla(sigla:'I',tipoVehiculo:'Carrotanque',tasa:10).save()
        new TasaFalla(sigla:'K',tipoVehiculo:'Camión 3 a 7 Ton',tasa:15).save()
        new TasaFalla(sigla:'N',tipoVehiculo:'Camión de 7 a 13',tasa:10).save()
        new TasaFalla(sigla:'O',tipoVehiculo:'Carrotanque',tasa:10).save()
        new TasaFalla(sigla:'J',tipoVehiculo:'REO',tasa:15).save()
        new TasaFalla(sigla:'V',tipoVehiculo:'Tractor',tasa:10).save()
        new TasaFalla(sigla:'Q',tipoVehiculo:'Grua',tasa:10).save()
        new TasaFalla(sigla:'R',tipoVehiculo:'Volqueta',tasa:10).save()
        new TasaFalla(sigla:'H',tipoVehiculo:'Buseta',tasa:15).save()
    }
    def destroy = {
    }
}
