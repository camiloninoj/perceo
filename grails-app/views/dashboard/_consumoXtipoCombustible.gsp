<%
    def consXtipoCombusCols = [['string', 'Tipo'], ['number', 'Consumo']]
%>

<gvisualization:barCoreChart
    elementId="consumoXtipoCombustible"
    title="${message(code: 'dashboard.consXtipoCombus.title', default: 'Consumo por tipo combustible')}"
    width="${580}"
    height="${280}"
    vAxis="${[title: 'Tipo', titleColor: 'red']}"
    columns="${consXtipoCombusCols}"
    data="${consXtipoCombusData}" />
<div id="consumoXtipoCombustible"></div>