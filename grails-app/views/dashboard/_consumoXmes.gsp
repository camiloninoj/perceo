<%
    def consXmesCols = [['string', 'Dia'], ['number', 'Consumo']]
    def companyPerformanceColumns = [['string', 'Year'], ['number', 'Sales'], ['number', 'Expenses']]
    def companyPerformanceData = [['2004', 1000, 400], ['2005', 1170, 460], ['2006', 660, 1120], ['2007', 1030, 540]]
%>

<gvisualization:columnCoreChart
    elementId="consumoXmes"
    title="${message(code: 'dashboard.consumoXmes.title', default: 'Consumo por mes')}"
    width="${1200}"
    height="${240}"
    hAxis="${[title: 'Dia', titleColor: 'red']}"
    columns="${consXmesCols}"
    data="${consXmesData}" />

<div id="consumoXmes"></div>