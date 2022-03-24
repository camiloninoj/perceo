<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'anexoT.label', default: 'Anexo T')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-anexoT" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-anexoT" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:jasperReport
                      jasper="anexo-t"
                      format="PDF,XLSX"
                      name="Anexo-T">
              <div class="fieldcontain">
                  <label for="unidadSigla">Cliente</label>
                  <g:if test="${cliente}">
                  <g:select name="unidadSigla"
                          from="${cliente}"
                          optionKey="unidadSigla" />
                  </g:if>
                  <g:else>
                  <g:select name="unidadSigla"
                          from="${co.lodiser.perceo.Cliente.list()}"
                          optionKey="unidadSigla" />
                  </g:else>
              </div>
              <div class="fieldcontain">
                <label for="mes">Mes</label>
                <g:select name="mes" from="${1..12}"/>
              </div>
              <div class="fieldcontain">
                 <label for="ano">Año</label>
                 <g:select name="ano" from="${2010..2021}"/>
               </div>
            </g:jasperReport>
        </div>
    </body>
</html>