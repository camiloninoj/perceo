<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Perceo"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <a class="navbar-brand" href="/#"><asset:image src="perceo-02.svg" alt="Perceo logo"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <g:pageProperty name="page.nav"/>
        </ul>
    </div>

</nav>

<g:layoutBody/>

<div class="footer row" role="contentinfo">
    <div class="col">
        <a href="http://www.lodiser.com.co/" target="_blank">
            <asset:image src="lodiser.svg" alt="Lodiser" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://www.lodiser.com.co/" target="_blank">Propiedad de Lodiser </a></strong>
        <p>Logistica de servicios digitales</p>

    </div>
    <div class="col">
        <a href="http://softart.com.co" target="_blank">
            <asset:image src="softart-logo-03.svg" alt="SoftArt" class="float-left"/>
        </a>
        <strong class="centered"><a href="http://softart.com.co" target="_blank">Desarrollado por SoftArt</a></strong>
        <p>Soluciones de software con diseño</p>

    </div>

    <div class="col">
        <asset:image src="derechos-autor.svg" alt="Derechos" class="float-left"/>
        <strong class="centered">Derechos </strong>
        <p><a href='https://www.freepik.es/vectores/coche'>Vector de Coche creado por macrovector - www.freepik.es</a></p>
        Iconos diseñados por <a href="http://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.es/" title="Flaticon"> www.flaticon.es</a>
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
