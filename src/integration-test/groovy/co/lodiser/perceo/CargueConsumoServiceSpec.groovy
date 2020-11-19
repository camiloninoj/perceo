package co.lodiser.perceo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CargueConsumoServiceSpec extends Specification {

    CargueConsumoService cargueConsumoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CargueConsumo(...).save(flush: true, failOnError: true)
        //new CargueConsumo(...).save(flush: true, failOnError: true)
        //CargueConsumo cargueConsumo = new CargueConsumo(...).save(flush: true, failOnError: true)
        //new CargueConsumo(...).save(flush: true, failOnError: true)
        //new CargueConsumo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cargueConsumo.id
    }

    void "test get"() {
        setupData()

        expect:
        cargueConsumoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CargueConsumo> cargueConsumoList = cargueConsumoService.list(max: 2, offset: 2)

        then:
        cargueConsumoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cargueConsumoService.count() == 5
    }

    void "test delete"() {
        Long cargueConsumoId = setupData()

        expect:
        cargueConsumoService.count() == 5

        when:
        cargueConsumoService.delete(cargueConsumoId)
        sessionFactory.currentSession.flush()

        then:
        cargueConsumoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CargueConsumo cargueConsumo = new CargueConsumo()
        cargueConsumoService.save(cargueConsumo)

        then:
        cargueConsumo.id != null
    }
}
