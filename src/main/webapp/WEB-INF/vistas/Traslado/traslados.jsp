<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Traslado</title>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<c:choose>
    <c:when test="${traslados.isEmpty()}">
        <h4>No hay traslados disponibles</h4>
    </c:when>
    <c:otherwise>
        <c:forEach items="${traslados}" var="traslado">
            <p>${traslado.derivacion.paciente.nombreCompleto}</p>
            <p>${traslado.derivacion.diagnostico}</p>
            <p>${traslado.derivacion.paraQueSector}</p>
        </c:forEach>
    </c:otherwise>
</body>
</html>