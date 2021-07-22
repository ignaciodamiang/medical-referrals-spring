<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Derivaciones</title>
</head>
<body>
<!-- se agrega la columna menu -->
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
    <div class="justify-content-between d-flex mt-3">
        <h2 class="mt-4">Derivaciones</h2>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                ${message}
        </div>
    </c:if>

    <c:choose>
        <c:when test="${derivaciones.isEmpty()}">
            <h4 class="mt-4 text-center">No hay Derivaciones disponibles</h4>
        </c:when>
        <c:otherwise>
            <table class="table mt-4 table-hover table-striped table-bordered border-primary">
                <thead>
                <tr>
                    <th scope="col" class="text-center">Paciente</th>
                    <th scope="col" class="text-center">Cobertura</th>
                    <th scope="col" class="text-center">Estado</th>
                    <th scope="col" class="text-center">Diagnostico</th>
                    <th scope="col" class="text-center">Fecha</th>
                    <th scope="col" class="text-center">Sector</th>
                    <th scope="col" class="text-center">Urgencia</th>
                    <th scope="col" class="text-center">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${derivaciones}" var="derivacion">
                    <tr>
                        <th scope="row" class="text-center">${derivacion.paciente.nombreCompleto}</th>
                        <td class="text-center">${derivacion.cobertura.nombre}</td>
                        <td class="text-center">${derivacion.estadoDerivacion}</td>
                        <td class="text-center">${derivacion.diagnostico}</td>
                        <td class="text-center">${derivacion.fechaDerivacion}</td>
                        <td class="text-center">${derivacion.paraQueSector}</td>

                        <c:choose>
                        <c:when test="${derivacion.getUrgente()}">
                            <td class="text-center" style="background: darkred;color: white;">URGENTE</td>
                        </c:when>
                        <c:otherwise>
                            <td class="text-center">---</td>
                        </c:otherwise>
                        </c:choose>
                        <td><a href="./ver-derivacion?id=${derivacion.getId()}" type="button" class="btn btn-info">Detalles</a></td>
<%--                                                                    <td>--%>
<%--                                                                        <!-- Button to Open the Modal -->--%>
<%--                                                                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#derivacion${derivacion.getId()}">--%>
<%--                                                                            Detalles--%>
<%--                                                                        </button>--%>
<%--                                                                        <!-- The Modal -->--%>
<%--                                                                        <div class="modal fade" id="derivacion${derivacion.getId()}">--%>
<%--                                                                            <div class="modal-dialog">--%>
<%--                                                                                <div class="modal-content">--%>
<%--                                                                                    <!-- Modal Header -->--%>
<%--                                                                                    <div class="modal-header">--%>
<%--                                                                                        <h4 class="modal-title">Detalles de derivacion</h4>--%>
<%--                                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--                                                                                    </div>--%>
<%--                                                                                    <!-- Modal body -->--%>
<%--                                                                                    <div class="modal-body">--%>
<%--                                                                                        <h4>Nombre de paciente: </h4>--%>
<%--                                                                                        <span>${derivacion.getPaciente().getNombreCompleto()}</span>--%>
<%--                                                                                        <h4>DNI: </h4>--%>
<%--                                                                                        <span>${derivacion.getPaciente().getDocumento()}</span>--%>
<%--                                                                                        <h4>Fecha nacimiento paciente: </h4>--%>
<%--                                                                                        <span>${derivacion.getPaciente().getFechaNacimiento()}</span>--%>
<%--                                                                                        <h4>Diagnostico:</h4>--%>
<%--                                                                                        <span>${derivacion.getDiagnostico()}</span>--%>
<%--                                                                                        <h4>Sector solicitado:</h4>--%>
<%--                                                                                        <span>${derivacion.getParaQueSector()}</span>--%>
<%--                                                                                        <h4>Cobertura:</h4>--%>
<%--                                                                                        <span>${derivacion.getCobertura().getNombre()}</span>--%>
<%--                                                                                    </div>--%>
<%--                                                                                    <!-- Modal footer -->--%>
<%--                                                                                    <div class="modal-footer">--%>
<%--                                                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>--%>
<%--                                                                                    </div>--%>
<%--                                                                                </div>--%>
<%--                                                                            </div>--%>
<%--                                                                        </div>--%>
<%--                                                                    </td>--%>
<%--                        <c:if test="${derivacion.getEstadoDerivacion().toString().equals('ENBUSQUEDA')}">--%>
<%--                            <td>--%>
<%--                                <div class="row justify-content-md-center">--%>
<%--                                    <a href="nueva-solicitud-derivacion/${derivacion.id}"class="btn btn-info mb-1 text-white"  role="button">Generar Solicitud</a>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${derivacion.getEstadoDerivacion().toString().equals('ENBUSQUEDA')}">--%>
<%--                        <td>--%>
<%--                            <div class="row justify-content-md-center">--%>
<%--                                <a href="verSolicitudes/${derivacion.id}"class="btn btn-success mb-1 text-white"  role="button">Ver Solicitud</a>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                        </c:if>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    </div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
