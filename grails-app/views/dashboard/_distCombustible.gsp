<%
    def distCombustibleCols = [['string', 'Tipo'], ['number', 'Distribucion']]
%>

<gvisualization:pieCoreChart
    elementId="distCombustible"
    title="Distribución de combustible"
    width="${400}"
    height="${240}"
    columns="${distCombustibleCols}"
    data="${distCombustibleData}" />
<div id="distCombustible"></div>