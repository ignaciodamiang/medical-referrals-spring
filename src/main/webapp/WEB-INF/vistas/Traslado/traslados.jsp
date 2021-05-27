<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Traslado</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
    <c:if test="${traslados.isEmpty()}">
        <h4>No hay traslados disponibles</h4>
    </c:if>
        <c:forEach items="${traslados}" var="traslado">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Traslado para el paciente ${traslado.derivacion.paciente.nombreCompleto}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">DU ${traslado.derivacion.paciente.documento}</h6>
                    <p class="card-text">Diagnostico: ${traslado.derivacion.diagnostico}</p>
                    <p class="card-text">Centro Médico: ${traslado.centroMedico.nombre}</p>
                    <p class="card-text">Sector: ${traslado.derivacion.paraQueSector}</p>
                    <p class="card-text">Dirección: ${traslado.centroMedico.direccion}</p>
                </div>
            </div>
        </c:forEach>
</body>
</html>