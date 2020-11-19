package co.lodiser.perceo

import grails.gorm.services.Service

@Service(Consumo)
interface ConsumoService {

    Consumo get(Serializable id)

    List<Consumo> list(Map args)

    Long count()

    void delete(Serializable id)

    Consumo save(Consumo detalleConsumo)

}