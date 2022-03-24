<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:javascript library='jquery' />
        <g:set var="entityName" value="${message(code: 'anexoT.label', default: 'Anexo K')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-anexoK" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-anexoO" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:jasperReport
                      jasper="anexo-k"
                      format="PDF,XLSX"
                      name="Anexo-K">

            <div class="fieldcontain">
                <label for="cliente">Cliente
                      <span class="required-indicator">*</span>
                </label>
                <g:if test="${cliente}">
                  <g:select id="cliente"
                          name="cliente.id"
                          from="${cliente}"
                          optionKey="id"
                          noSelection="[null:' ']"
                          required=""
                          onchange="cambiarCliente(this.value);"/>
                </g:if>
                <g:else>
                  <g:select id="cliente"
                          name="cliente.id"
                          from="${co.lodiser.perceo.Cliente.list()}"
                          optionKey="id"
                          noSelection="[null:' ']"
                          required=""
                          onchange="cambiarCliente(this.value);"/>
                </g:else>
                  <script>
                    function cambiarCliente(idCliente) {
                        jQuery.ajax({type:'POST',data:'idCliente='+idCliente, url:'/anexoK/encontrarVehiculos',success:function(data,textStatus){jQuery('#vehiculo').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){}});
                    }
                </script>
            </div>
                <g:include action="show" id="1" />
              <div class="fieldcontain">
                <label for="fechaInicio">
                    Fecha inicio
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="fechaInicio" precision="day" required=""/>
              </div>
                <div class="fieldcontain">
                    <label for="fechaFin">
                        Fecha fin
                        <span class="required-indicator">*</span>
                    </label>
                    <g:datePicker name="fechaFin" precision="day" required=""/>
                </div>
              <div class="fieldcontain">
                 <label for="vehiculo">Vehiculo</label>
                  <span id="vehiculo"></span>
               </div>
            </g:jasperReport>
        </div>
    </body>
</html>