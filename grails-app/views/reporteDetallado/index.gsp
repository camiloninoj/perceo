<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'reporteDetallado.label', default: 'Reporte detallado')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-reporteDetallado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-reporteDetallado" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${reporteDetalladoList}" maxProperties="14" except="id"/>

            <div class="pagination">
                <g:paginate total="${reporteDetalladoCount ?: 0}" />
            </div>
            <export:formats />
            <g:jasperReport
                      jasper="Cherry_Landscape"
                      format="PDF"
                      name="Parameter Example">
            </g:jasperReport>
        </div>
    </body>
</html>