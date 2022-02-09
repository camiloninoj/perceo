<div id="consumoXPeriodo">
    <table>
        <tr>
            <th>Periodo</th>
            <th>Unidad</th>
            <th>Consumo en $</th>
            <th>Consumo en Gl</th>
        </tr>
        <g:each in="${consumoXPeriodoData}" var="c">
            <tr>
                <td>${c.periodo}</td>
                <td>${c.unidadSigla}</td>
                <td>${c.totalPesos}</td>
                <td>${c.totalGalones}</td>
            </tr>
        </g:each>
    </table>
</div>