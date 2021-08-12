package co.lodiser.perceo

import grails.gorm.services.Service

@Service(Destino)
interface DestinoService {

    Destino get(Serializable id)

    List<Destino> list(Map args)

    Long count()

    void delete(Serializable id)

    Destino save(Destino destino)

}