package co.lodiser.perceo

class AnexoOController {

    // Export service provided by Export plugin
    def exportService

    def index() {
        model:[cliente: authenticatedUser.cliente]
    }
}
