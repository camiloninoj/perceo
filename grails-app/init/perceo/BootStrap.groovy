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

        def adminRole = new Rol(authority: 'ROLE_ADMIN').save()
        def gestorRole = new Rol(authority: 'ROLE_GESTOR').save()
        def auditorRole = new Rol(authority: 'ROLE_AUDITOR').save()

        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }

        //GERENCIAL
        new Requestmap(url: '/dashboard/**',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()

        //PARAMETROS
        new Requestmap(url: '/cliente/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/cliente/create',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cliente/save',configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/cliente/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/cliente/edit/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cliente/update/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cliente/delete/*',configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/tasaFalla/**',configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/destino/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/destino/create',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/destino/save',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/destino/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/destino/edit/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/destino/update/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/destino/delete/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()

        new Requestmap(url: '/cargueDestino/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueDestino/create',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueDestino/save',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueDestino/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueDestino/delete/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()

        //REGISTROS
        new Requestmap(url: '/vehiculo/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/vehiculo/create',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/vehiculo/save',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/vehiculo/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/vehiculo/edit/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/vehiculo/update/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/vehiculo/delete/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()

        new Requestmap(url: '/cargueVehiculo/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueVehiculo/create',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueVehiculo/save',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueVehiculo/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueVehiculo/delete/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()

        new Requestmap(url: '/consumo/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/consumo/create',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/consumo/save',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/consumo/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/consumo/edit/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/consumo/update/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/consumo/delete/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()

        new Requestmap(url: '/cargueConsumo/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueConsumo/create',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueConsumo/save',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueConsumo/show/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()
        new Requestmap(url: '/cargueConsumo/delete/*',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR').save()

        //REPORTES
        new Requestmap(url: '/reporteDetallado/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/reporteDetallado/show',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()

        new Requestmap(url: '/anexoT/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/anexoO/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()

        new Requestmap(url: '/anexoK/index',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()
        new Requestmap(url: '/anexoK/encontrarVehiculos',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()

        new Requestmap(url: '/jasper/**',configAttribute: 'ROLE_ADMIN,ROLE_GESTOR,ROLE_AUDITOR').save()

        //SEGURIDAD
        new Requestmap(url: '/usuario/**',configAttribute: 'ROLE_ADMIN').save()

        def admin = new Usuario(username:'admin',password:'11226117').save()
        def gestor = new Usuario(username:'gestor',password:'gestor').save()
        def auditor = new Usuario(username:'auditor',password:'auditor').save()

        UsuarioRol.create(admin, adminRole)
        UsuarioRol.create(gestor, gestorRole)
        UsuarioRol.create(auditor, auditorRole)

        springSecurityService.clearCachedRequestmaps()
    }

    def destroy = {
    }
}
