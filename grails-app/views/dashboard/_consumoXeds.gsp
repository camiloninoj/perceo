<%
    def consXedsCols = [['string', 'EDS'], ['number', 'Consumo']]
%>

<gvisualization:barCoreChart
    elementId="consumoXeds"
    title="${message(code: 'dashboard.consXeds.title', default: 'Consumo por estación de servicio')}"
    width="${580}"
    height="${280}"
    vAxis="${[title: 'EDS', titleColor: 'red']}"
    columns="${consXedsCols}"
    data="${conXedsData}" />
<div id="consumoXeds"></div>