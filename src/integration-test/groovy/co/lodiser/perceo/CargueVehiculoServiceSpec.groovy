package co.lodiser.perceo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CargueVehiculoServiceSpec extends Specification {

    CargueVehiculoService cargueVehiculoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CargueVehiculo(...).save(flush: true, failOnError: true)
        //new CargueVehiculo(...).save(flush: true, failOnError: true)
        //CargueVehiculo cargueVehiculo = new CargueVehiculo(...).save(flush: true, failOnError: true)
        //new CargueVehiculo(...).save(flush: true, failOnError: true)
        //new CargueVehiculo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cargueVehiculo.id
    }

    void "test get"() {
        setupData()

        expect:
        cargueVehiculoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CargueVehiculo> cargueVehiculoList = cargueVehiculoService.list(max: 2, offset: 2)

        then:
        cargueVehiculoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cargueVehiculoService.count() == 5
    }

    void "test delete"() {
        Long cargueVehiculoId = setupData()

        expect:
        cargueVehiculoService.count() == 5

        when:
        cargueVehiculoService.delete(cargueVehiculoId)
        sessionFactory.currentSession.flush()

        then:
        cargueVehiculoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CargueVehiculo cargueVehiculo = new CargueVehiculo()
        cargueVehiculoService.save(cargueVehiculo)

        then:
        cargueVehiculo.id != null
    }
}
