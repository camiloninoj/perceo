package co.lodiser.perceo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CargueDestinoServiceSpec extends Specification {

    CargueDestinoService cargueDestinoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CargueDestino(...).save(flush: true, failOnError: true)
        //new CargueDestino(...).save(flush: true, failOnError: true)
        //CargueDestino cargueDestino = new CargueDestino(...).save(flush: true, failOnError: true)
        //new CargueDestino(...).save(flush: true, failOnError: true)
        //new CargueDestino(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cargueDestino.id
    }

    void "test get"() {
        setupData()

        expect:
        cargueDestinoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CargueDestino> cargueDestinoList = cargueDestinoService.list(max: 2, offset: 2)

        then:
        cargueDestinoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cargueDestinoService.count() == 5
    }

    void "test delete"() {
        Long cargueDestinoId = setupData()

        expect:
        cargueDestinoService.count() == 5

        when:
        cargueDestinoService.delete(cargueDestinoId)
        sessionFactory.currentSession.flush()

        then:
        cargueDestinoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CargueDestino cargueDestino = new CargueDestino()
        cargueDestinoService.save(cargueDestino)

        then:
        cargueDestino.id != null
    }
}
