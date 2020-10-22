package co.lodiser.perceo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TasaFallaServiceSpec extends Specification {

    TasaFallaService tasaFallaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TasaFalla(...).save(flush: true, failOnError: true)
        //new TasaFalla(...).save(flush: true, failOnError: true)
        //TasaFalla tasaFalla = new TasaFalla(...).save(flush: true, failOnError: true)
        //new TasaFalla(...).save(flush: true, failOnError: true)
        //new TasaFalla(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //tasaFalla.id
    }

    void "test get"() {
        setupData()

        expect:
        tasaFallaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TasaFalla> tasaFallaList = tasaFallaService.list(max: 2, offset: 2)

        then:
        tasaFallaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        tasaFallaService.count() == 5
    }

    void "test delete"() {
        Long tasaFallaId = setupData()

        expect:
        tasaFallaService.count() == 5

        when:
        tasaFallaService.delete(tasaFallaId)
        sessionFactory.currentSession.flush()

        then:
        tasaFallaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TasaFalla tasaFalla = new TasaFalla()
        tasaFallaService.save(tasaFalla)

        then:
        tasaFalla.id != null
    }
}
