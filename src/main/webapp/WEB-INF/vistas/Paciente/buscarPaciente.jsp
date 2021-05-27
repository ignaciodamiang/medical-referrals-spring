<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <c:if test="${not empty error}">
        <div class="alert alert-warning" role="alert">
            <p>${error}</p>
        </div>
    </c:if>

    <c:if test="${not empty paciente}">
    <div>
        <p>Paciente ${paciente.nombreCompleto}</p>
        <p>${paciente.documento}</p>
        <p>${paciente.fechaNacimiento}</p>
        <a href="/nueva-derivacion?id=${paciente.id}">Generar nueva derivación</a>
    </div>
    </c:if>


</body>
</html>
