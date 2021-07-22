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
            <table class="table table-hover table-striped table-bordered border-primary">
                <thead>
                <tr>
                    <th scope="col" class="text-center">Código</th>
                    <th scope="col" class="text-center">Paciente</th>
                    <th scope="col" class="text-center">Cobertura</th>
                    <th scope="col" class="text-center">Estado</th>
                    <th scope="col" class="text-center">Diagnostico</th>
                    <th scope="col" class="text-center">Fecha</th>
                    <th scope="col" class="text-center">Sector</th>
                    <th scope="col" class="text-center">Urgencia</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${derivaciones}" var="derivacion">
                    <tr>
                        <th scope="row"><a href="ver-derivacion?id=${derivacion.getId()}">${derivacion.getCodigo()}</a></th>
                        <td scope="row" class="text-center">${derivacion.paciente.nombreCompleto}</td>
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
