package co.lodiser.perceo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DestinoServiceSpec extends Specification {

    DestinoService destinoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Destino(...).save(flush: true, failOnError: true)
        //new Destino(...).save(flush: true, failOnError: true)
        //Destino destino = new Destino(...).save(flush: true, failOnError: true)
        //new Destino(...).save(flush: true, failOnError: true)
        //new Destino(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //destino.id
    }

    void "test get"() {
        setupData()

        expect:
        destinoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Destino> destinoList = destinoService.list(max: 2, offset: 2)

        then:
        destinoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        destinoService.count() == 5
    }

    void "test delete"() {
        Long destinoId = setupData()

        expect:
        destinoService.count() == 5

        when:
        destinoService.delete(destinoId)
        sessionFactory.currentSession.flush()

        then:
        destinoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Destino destino = new Destino()
        destinoService.save(destino)

        then:
        destino.id != null
    }
}
