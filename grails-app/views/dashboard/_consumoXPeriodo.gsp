<div id="consumoXPeriodo">
    <table>
        <tr>
            <th>Periodo</th>
            <th>Unidad</th>
            <th>Consumo en $</th>
            <th>Consumo en Gl</th>
            <th>Cantidad por tipo</th>
            <th>Valor por tipo</th>
        </tr>
        <g:each in="${consumoXPeriodoData}" var="c">
            <tr>
                <td></td>
                <td>${c.unidadSigla}</td>
                <td>${c.totalPesos}</td>
                <td>${c.totalGalones}</td>
                <td>${c.totalesConsumo}</td>
                <td>${c.totalesImporte}</td>
            </tr>
        </g:each>
    </table>
</div>