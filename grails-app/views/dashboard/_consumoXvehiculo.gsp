<%
    def consXvehiculoCols = [['string', 'Vehiculo'], ['number', 'DIESEL'], ['number', 'CORRIENTE'], ['number', 'EXTRA']]
%>
<gvisualization:barCoreChart
    elementId="consumoXvehiculo"
    title="${message(code: 'dashboard.consumoXvehiculo.title', default: 'Consumo por vehiculo')}"
    width="${580}"
    height="${280}"
    vAxis="${[title: 'Vehiculo', titleColor: 'red']}"
    columns="${consXvehiculoCols}"
    data="${consXvehiculoData}" />
<div id="consumoXvehiculo"></div>