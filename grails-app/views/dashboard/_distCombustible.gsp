<%
    def distCombustibleCols = [['string', 'Tipo'], ['number', 'Distribucion']]
%>

<gvisualization:pieCoreChart
    elementId="distCombustible"
    title="DISTRIBUCIÓN DE COMBUSTIBLE"
    width="${450}"
    height="${300}"
    is3D="${true}"
    columns="${distCombustibleCols}"
    data="${distCombustibleData}" />
<div id="distCombustible"></div>