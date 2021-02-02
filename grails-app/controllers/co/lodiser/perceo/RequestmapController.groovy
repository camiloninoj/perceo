package co.lodiser.perceo

class RequestmapController {

    def springSecurityService

    def clear() {
        springSecurityService.clearCachedRequestmaps()

        flash.message = 'Cache limpiado'
    }
}
