<%
    def consumoXTipoVehiculoCols = [['string', 'Tipo Vehículo'],['number', 'Consumo']]
%>

<gvisualization:barCoreChart
    elementId="consumoXTipoVehiculo"
    title="${message(code: 'dashboard.consumoXTipoVehiculo.title', default: 'Consumo por tipo de vehículo')}"
    width="${400}"
    height="${240}"
    vAxis="${[title: 'Tipo Vehículo', titleColor: 'red']}"
    columns="${consumoXTipoVehiculoCols}"
    data="${consumoXTipoVehiculoData}" />
<div id="consumoXTipoVehiculo"></div>