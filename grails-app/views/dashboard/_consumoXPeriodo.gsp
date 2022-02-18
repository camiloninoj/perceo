<div id="consumoXPeriodo" class="title">
    <g:each in="${consumoXPeriodoData}" var="c">
        <div>PERIODO ${c.periodo}</div>
        <div>UNIDAD ${c.unidadSigla}</div>
        <div>CONSUMO ${c.totalPesos}</div>
    </g:each>
</div>