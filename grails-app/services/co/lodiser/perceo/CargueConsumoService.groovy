package co.lodiser.perceo

import grails.gorm.services.Service

@Service(CargueConsumo)
interface CargueConsumoService {

    CargueConsumo get(Serializable id)

    List<CargueConsumo> list(Map args)

    Long count()

    void delete(Serializable id)

    CargueConsumo save(CargueConsumo cargueConsumo)

}