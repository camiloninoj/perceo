package co.lodiser.perceo

import grails.gorm.services.Service

@Service(CargueDestino)
interface CargueDestinoService {

    CargueDestino get(Serializable id)

    List<CargueDestino> list(Map args)

    Long count()

    void delete(Serializable id)

    CargueDestino save(CargueDestino cargueDestino)

}