package perceo

import co.lodiser.perceo.Cliente
import co.lodiser.perceo.TasaFalla
import co.lodiser.perceo.Rol
import co.lodiser.perceo.UsuarioRol
import co.lodiser.perceo.Usuario
import co.lodiser.perceo.Requestmap

class BootStrap {

    def springSecurityService

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

        new Cliente(unidadSigla:'BAS03', unidadNombre:'BATALLON DE ASPC No. 3',
                divisionSigla:'DIV03',divisionNombre:'TERCERA DIVISION',
                brigadaSigla:'BR03',brigadaNombre:'TERCERA BRIGADA',
                ciudad:'Cali',
                gestorFlotaSigla:'CP.',gestorFlotaNombre:'VALENZUELA PINZON OSCAR MAURICIO',
                oficialS4Sigla:'CT.', oficialS4Nombre:'AMAYA TOLEDO LUIS GUILLERMO',
                ejecutivoSigla:'MY.',ejecutivoNombre:'GARCIA SANCHEZ JOHN A.').save()

        def adminRole = new Rol(authority: 'ROLE_ADMIN').save()

        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }

        new Requestmap(url: '/cliente/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/tasaFalla/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cargueVehiculo/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/vehiculo/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cargueConsumo/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/consumo/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/reporteDetallado/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/anexoT/**',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/jasper/**',configAttribute: 'ROLE_ADMIN').save()

        springSecurityService.clearCachedRequestmaps()

        def admin = new Usuario(username:'admin',password:'p3R(30').save()

        UsuarioRol.create(admin, adminRole)
    }

    def destroy = {
    }
}
