package co.lodiser.perceo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ConsumoServiceSpec extends Specification {

    ConsumoService detalleConsumoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DetalleConsumo(...).save(flush: true, failOnError: true)
        //new DetalleConsumo(...).save(flush: true, failOnError: true)
        //DetalleConsumo detalleConsumo = new DetalleConsumo(...).save(flush: true, failOnError: true)
        //new DetalleConsumo(...).save(flush: true, failOnError: true)
        //new DetalleConsumo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //detalleConsumo.id
    }

    void "test get"() {
        setupData()

        expect:
        detalleConsumoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Consumo> detalleConsumoList = detalleConsumoService.list(max: 2, offset: 2)

        then:
        detalleConsumoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        detalleConsumoService.count() == 5
    }

    void "test delete"() {
        Long detalleConsumoId = setupData()

        expect:
        detalleConsumoService.count() == 5

        when:
        detalleConsumoService.delete(detalleConsumoId)
        sessionFactory.currentSession.flush()

        then:
        detalleConsumoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Consumo detalleConsumo = new Consumo()
        detalleConsumoService.save(detalleConsumo)

        then:
        detalleConsumo.id != null
    }
}
