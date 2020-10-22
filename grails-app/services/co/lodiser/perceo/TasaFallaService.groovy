package co.lodiser.perceo

import grails.gorm.services.Service

@Service(TasaFalla)
interface TasaFallaService {

    TasaFalla get(Serializable id)

    List<TasaFalla> list(Map args)

    Long count()

    void delete(Serializable id)

    TasaFalla save(TasaFalla tasaFalla)

}