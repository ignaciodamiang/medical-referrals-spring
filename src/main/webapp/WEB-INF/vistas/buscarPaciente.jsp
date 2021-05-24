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

    <div>
        <p>${paciente.nombreCompleto}</p>
        <p>${paciente.documento}</p>
        <p>${paciente.fechaNacimiento}</p>
    </div>
</body>
</html>
