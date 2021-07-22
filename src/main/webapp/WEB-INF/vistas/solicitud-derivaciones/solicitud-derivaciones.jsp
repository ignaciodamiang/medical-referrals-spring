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
            <h3 class="mt-4 text-center">No hay Solicitudes disponibles</h3>
        </c:when>
        <c:otherwise>
            <table class="table table-hover table-striped table-bordered">
                <thead>
                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Derivacion</th>
                    <th scope="col">Centro Medico</th>
                    <th scope="col">Diagnostico</th>
                    <th scope="col">Descripcion</th>
                    <th scope="col">Aceptado</th>
                    <th scope="col">Confirmado</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Urgencia</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listaSolicitudesDerivaciones}" var="solicitud">
                    <tr>
                        <td><a href="ver-solicitud-derivacion/${solicitud.getId()}">${solicitud.codigo}</a></td>
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
                                <td class="text-center">No</td>
                            </c:otherwise>
                        </c:choose>
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

