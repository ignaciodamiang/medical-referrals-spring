<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Traslados en Curso</title>
</head>
<body>
    <c:forEach items="${traslados}" var="traslado">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Traslado del Paciente: ${traslado.derivacion.paciente.nombreCompleto}</h5>
                <p class="card-text"> Centro medico: ${traslado.centroMedico.nombre}</p>
                <p class="card-text">Destino: ${traslado.centroMedico.direccion}</p>
                <a href="finalizarTraslado/${traslado.id}" class="btn btn-success">
                    Finalizar
                </a>
                <a href="cancelarTraslado/${traslado.id}" class="btn btn-danger">
                    Cancelar
                </a>
            </div>
        </div>
    </c:forEach>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
