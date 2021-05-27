<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link  href="${css}/style.css"  rel="stylesheet"/>
    <title>Solicitud de Derivaciones</title>
</head>
<body>

<div class="container">
    <div class="justify-content-between d-flex mt-3">
        <h2 class="mt-4">Solicitud de derivaciones</h2>
        <a href="nueva-solicitud-derivacion" style="text-decoration: none">
            <button type="button"  class="btn text-white mt-4" style="background-color:#d35400">Agregar solicitud de derivación</button>
        </a>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                ${message}
        </div>
    </c:if>

    <c:choose>
        <c:when test="${listaSolicitudDerivaciones.isEmpty()}">
            <h4 class="mt-4 text-center">No hay Solicitudes disponibles</h4>
        </c:when>
        <c:otherwise>
            <table class="table mt-4">
                <thead>
                <tr>
                    <th scope="col">Derivacion</th>
                    <th scope="col">Centro Medico</th>
                    <th scope="col">Aceptado</th>
                    <th scope="col">Confirmado</th>
                    <th scope="col">Fecha</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listaSolicitudesDerivaciones}" var="solicitud">
                    <tr>
                        <td>${solicitud.getDerivacion().getFinalizada()}</td>
                        <td>${solicitud.getCentroMedico().getNombre()}</td>
                        <td>${solicitud.getAceptado()}</td>
                        <td>${solicitud.getConfirmado()}</td>
                        <td>${solicitud.getFechaCreacion().toLocaleString()}</td>
                        <c:if test="${solicitud.aceptado == false}">
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="aceptarSolicitud/${solicitud.id}"class="btn btn-success  text-white"  role="button">Aceptar</a>
                            </div>
                        </td>
                        </c:if>
                        <c:if test="${solicitud.aceptado == true}">
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="rechazarSolicitud/${solicitud.id}"class="btn btn-danger  text-white"  role="button">Rechazar</a>
                            </div>
                        </td>
                        </c:if>
                        <c:if test="${solicitud.aceptado == true}">
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="crearTraslado/${solicitud.id}"class="btn btn-info  text-white"  role="button">Generar Traslado</a>
                            </div>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

