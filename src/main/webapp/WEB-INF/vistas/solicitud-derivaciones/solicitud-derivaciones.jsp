<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Solicitud de Derivaciones</title>
</head>
<body>
<!-- se agrega la columna menu -->
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
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
                    <th scope="col">Diagnostico</th>
                    <th scope="col">Descripcion</th>
                    <th scope="col">Aceptado</th>
                    <th scope="col">Confirmado</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Urgencia</th>
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
                        <td>${solicitud.getDerivacion().getDiagnostico()}</td>
                        <td>${solicitud.getDescripcion()}</td>
                        <td>${solicitud.getAceptado()}</td>
                        <td>${solicitud.getConfirmado()}</td>
                        <td>${solicitud.getFechaCreacion().toLocaleString()}</td>
                        <c:choose>
                            <c:when test="${solicitud.getDerivacion().getUrgente()}">
                                <td class="text-center" style="background: darkred;color: white;">URGENTE</td>
                            </c:when>
                            <c:otherwise>
                                <td class="text-center">---</td>
                            </c:otherwise>
                        </c:choose>
                                        <td>

<%--                                            <div class="modal-header">--%>
<%--                                                <h4 class="modal-title">Detalles de Solicitud</h4>--%>
<%--                                                <div class="m-auto btn btn-danger" data-dismiss="modal">URGENTE</div>                                                  <button type="button" class="close" data-dismiss="modal">×</button>--%>
<%--                                            </div>--%>
                                            <!-- Button to Open the Modal -->
                                            <a href="ver-solicitud-derivacion/${solicitud.getId()}" class="btn btn-info">
                                                Detalles
                                            </a>
                                            <!-- The Modal -->
<%--                                            <div class="modal fade" id="solicitud${solicitud.getId()}">--%>
<%--                                                <div class="modal-dialog">--%>
<%--                                                    <div class="modal-content">--%>
<%--                                                        <!-- Modal Header -->--%>
<%--                                                        <c:choose>--%>
<%--                                                        <c:when test="${solicitud.getDerivacion().getUrgente()}">--%>
<%--                                                            <div class="modal-header">--%>
<%--                                                            <h4 class="modal-title">Detalles de Solicitud</h4>--%>
<%--                                                            <div class="m-auto btn btn-danger">URGENTE</div>--%>
<%--                                                            <button type="button" class="close" data-dismiss="modal">×</button>--%>
<%--                                                            </div>--%>
<%--                                                        </c:when>--%>
<%--                                                        <c:otherwise>--%>
<%--                                                            <div class="modal-header">--%>
<%--                                                                <h4 class="modal-title">Detalles de Solicitud</h4>--%>
<%--                                                                <button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--                                                            </div>--%>
<%--                                                        </c:otherwise>--%>
<%--                                                        </c:choose>--%>

<%--                                                        <!-- Modal body -->--%>
<%--                                                        <div class="modal-body">--%>
<%--                                                            <h4>Nombre de paciente: </h4>--%>
<%--                                                            <span>${solicitud.getDerivacion().getPaciente().getNombreCompleto()}</span>--%>
<%--                                                            <h4>DNI: </h4>--%>
<%--                                                            <span>${solicitud.getDerivacion().getPaciente().getDocumento()}</span>--%>
<%--                                                            <h4>Fecha nacimiento paciente: </h4>--%>
<%--                                                            <span>${solicitud.getDerivacion().getPaciente().getFechaNacimiento()}</span>--%>
<%--                                                            <h4>Diagnostico:</h4>--%>
<%--                                                            <span>${solicitud.getDerivacion().getDiagnostico()}</span>--%>
<%--                                                            <h4>Sector solicitado:</h4>--%>
<%--                                                            <span>${solicitud.getDerivacion().getParaQueSector()}</span>--%>
<%--                                                            <h4>Cobertura:</h4>--%>
<%--                                                            <span>${solicitud.getDerivacion().getCobertura().getNombre()}</span>--%>
<%--                                                        </div>--%>
<%--                                                        <!-- Modal footer -->--%>
<%--                                                        <div class="modal-footer">--%>
<%--                                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </div>--%>
<%--                                            </div>--%>
                                        </td>
<%--                        <c:if test="${(solicitud.aceptado == false) && rol =='Administrativo'}">--%>
<%--                        <td>--%>
<%--                            <div class="row justify-content-md-center">--%>
<%--                                <a href="aceptarSolicitud/${solicitud.id}"class="btn btn-success  text-white"  role="button">Aceptar</a>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${solicitud.aceptado == true && rol =='Administrativo'}">--%>
<%--                        <td>--%>
<%--                            <div class="row justify-content-md-center">--%>
<%--                                <a href="rechazarSolicitud/${solicitud.id}"class="btn btn-danger  text-white"  role="button">Rechazar</a>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                        </c:if>--%>
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

        <c:if test="${rol =='Administrativo'}">
        <div class="row justify-content-md-center">
            <a href="ver-traslados-encurso"class="btn text-white mt-4" style="background-color:#d35400" role="button">Ver Traslados en Curso</a>
        </div>
        </c:if>
</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>

