package co.lodiser.perceo

import grails.gorm.services.Service

@Service(CargueVehiculo)
interface CargueVehiculoService {

    CargueVehiculo get(Serializable id)

    List<CargueVehiculo> list(Map args)

    Long count()

    void delete(Serializable id)

    CargueVehiculo save(CargueVehiculo cargueVehiculo)

}