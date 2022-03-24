<div class="fieldcontain">
    <label for="cliente">Cliente</label>
    <g:if test="${cliente}">
        <g:select id="cliente" name='cliente.id' value="${cargueVehiculo?.cliente?.id}"
            from='${cliente}'
            optionKey="id" />
    </g:if>
    <g:else>
        <g:select id="cliente" name='cliente.id' value="${cargueVehiculo?.cliente?.id}"
            noSelection="${['null':' ']}"
            from='${co.lodiser.perceo.Cliente.list()}'
            optionKey="id" />
    </g:else>
</div>