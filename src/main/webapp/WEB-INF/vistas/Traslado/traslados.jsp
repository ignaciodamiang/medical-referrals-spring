<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Traslado</title>
   </head>
<body>
<!-- se agrega la columna menu -->
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-10" id="main">
    <!--  fin menu -->
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
    <!--  footer -->
</div>

<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>