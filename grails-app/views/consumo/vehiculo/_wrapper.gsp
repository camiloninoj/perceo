<div class="fieldcontain">
    <label for="vehiculo">Cliente<span class='required-indicator'>*</span></label>
    <g:if test="${vehiculos}">
        <g:select id="vehiculo" name='vehiculo.id' value="${consumo?.vehiculo?.id}"
            from='${vehiculos}'
            optionKey="id" />
    </g:if>
    <g:else>
        <g:select id="vehiculo" name='vehiculo.id' value="${consumo?.vehiculo?.id}"
            from='${co.lodiser.perceo.Vehiculo.list()}'
            optionKey="id" />
    </g:else>
</div>