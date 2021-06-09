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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link  href="${css}/style.css"  rel="stylesheet"/>
    <title>Solicitud de Derivaciones</title>
</head>
<body>

<div class="container">
    <div class="justify-content-between d-flex mt-3">
        <h2 class="mt-4">Solicitud de derivaciones</h2>
            <c:if test="${rol =='Derivador'}">
                <a href="../listado-derivacion" style="text-decoration: none">
                    <button type="button"  class="btn text-white mt-4" style="background-color:#d35400">Volver</button>
                </a>
            </c:if>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                ${message}
        </div>
    </c:if>

    <c:choose>
        <c:when test="${listaSolicitudesDerivaciones.isEmpty()}">
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
                        <td>${solicitud.getDerivacion().getEstadoDerivacion().toString()}</td>
                        <td>${solicitud.getCentroMedico().getNombre()}</td>
                        <td>${solicitud.getAceptado()}</td>
                        <td>${solicitud.getConfirmado()}</td>
                        <td>${solicitud.getFechaCreacion().toLocaleString()}</td>
                                        <td>
                                            <!-- Button to Open the Modal -->
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#solicitud${solicitud.getId()}">
                                                Detalles
                                            </button>
                                            <!-- The Modal -->
                                            <div class="modal fade" id="solicitud${solicitud.getId()}">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <!-- Modal Header -->
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">Detalles de Solicitud</h4>
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        </div>
                                                        <!-- Modal body -->
                                                        <div class="modal-body">
                                                            <h4>Nombre de paciente: </h4>
                                                            <span>${solicitud.getDerivacion().getPaciente().getNombreCompleto()}</span>
                                                            <h4>DNI: </h4>
                                                            <span>${solicitud.getDerivacion().getPaciente().getDocumento()}</span>
                                                            <h4>Fecha nacimiento paciente: </h4>
                                                            <span>${solicitud.getDerivacion().getPaciente().getFechaNacimiento()}</span>
                                                            <h4>Diagnostico:</h4>
                                                            <span>${solicitud.getDerivacion().getDiagnostico()}</span>
                                                            <h4>Sector solicitado:</h4>
                                                            <span>${solicitud.getDerivacion().getParaQueSector()}</span>
                                                            <h4>Cobertura:</h4>
                                                            <span>${solicitud.getDerivacion().getCobertura().getNombre()}</span>
                                                        </div>
                                                        <!-- Modal footer -->
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                        <c:if test="${(solicitud.aceptado == false) && rol =='Administrativo'}">
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="aceptarSolicitud/${solicitud.id}"class="btn btn-success  text-white"  role="button">Aceptar</a>
                            </div>
                        </td>
                        </c:if>
                        <c:if test="${solicitud.aceptado == true && rol =='Administrativo'}">
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="rechazarSolicitud/${solicitud.id}"class="btn btn-danger  text-white"  role="button">Rechazar</a>
                            </div>
                        </td>
                        </c:if>
                        <c:if test="${solicitud.aceptado == true && rol =='Derivador'}">
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="../crearTraslado/${solicitud.id}"class="btn btn-info  text-white"  role="button">Generar Traslado</a>
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
        <c:if test="${rol =='Administrativo'}">
        <div class="row justify-content-md-center">
            <a href="ver-traslados-en curso"class="btn btn-block  text-white"  role="button">Ver Traslados en Curso</a>
        </div>
        </c:if>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>

