<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bienvenido a Perceo</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${message(code: 'parametros.label', default: 'Parametros')} <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><g:link controller="cliente"><g:message code="clientes.label" default="Clientes"/></g:link></li>
            <li class="dropdown-item"><g:link controller="tasaFalla"><g:message code="tasasFalla.label" default="Tasas de falla"/></g:link></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${message(code: 'registros.label', default: 'Registros')} <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><g:link controller="vehiculo"><g:message code="vehiculos.label" default="Vehiculos"/></g:link></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${message(code: 'reportes.label', default: 'Reportes')} <span class="caret"></span></a>
        <ul class="dropdown-menu">
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${message(code: 'usuarios.label', default: 'Usuarios')} <span class="caret"></span></a>
        <ul class="dropdown-menu">
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="portada-perceo.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Perceo</h1>

        <p>
           Seguimiento a la gestión de flotas: Consumo de combustible, Documentos anexos de consumo,
           Entrega de voucher, Dinámico de libros digitales, Gestión de plantillas,
           Rendimientos históricos, Consumos de centros de costo, Consumos por equipos,
           Revisión de trazabilidad, Indicadores de desempeño
        </p>
    </section>
</div>

</body>
</html>
