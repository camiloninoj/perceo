<%
    def distCombustibleCols = [['string', 'Tipo'], ['number', 'Distribucion']]
%>

<gvisualization:pieCoreChart
    elementId="distCombustible"
    title="Distribución de combustible"
    width="${580}"
    height="${280}"
    columns="${distCombustibleCols}"
    data="${distCombustibleData}" />
<div id="distCombustible"></div>