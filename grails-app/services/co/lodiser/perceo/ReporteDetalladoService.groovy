package co.lodiser.perceo

import grails.gorm.services.Service

@Service(ReporteDetallado)
interface ReporteDetalladoService {

    ReporteDetallado get(Serializable id)

    List<ReporteDetallado> list(Map args)

    Long count()
}