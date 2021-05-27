<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="ObtenerPaciente" method="post">
        <label for="documento">Numero de Documento del Paciente</label>
        <input name="documento" type="number" id="documento"/>
        <button type="submit">Buscar</button>
    </form>

    <c:if test="${not empty paciente}">
    <div>
        <p>Paciente ${paciente.nombreCompleto}</p>
        <p>${paciente.documento}</p>
        <p>${paciente.fechaNacimiento}</p>
        <a href="<c:url value="/nueva-derivacion/${idPaciente}"/>">Generar nueva derivación</a>
    </div>
    </c:if>
</body>
</html>
