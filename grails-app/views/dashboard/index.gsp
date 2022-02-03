<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:javascript library='jquery' />
        <gvisualization:apiImport/>
        <g:set var="entityName" value="${message(code: 'dashboard.label', default: 'Dashboard')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-anexoK" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-dashboard" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:render template="consumoXtipoCombustible" bean="${consXtipoCombusData}"/>
            <g:render template="consumoXeds" bean="${conXedsData}"/>
            <g:render template="consumoXvehiculo" bean="${consXvehiculoData}"/>
            <g:render template="consumoXmes" bean="${consXmesData}"/>
        </div>
    </body>
</html>