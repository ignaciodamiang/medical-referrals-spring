<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Title</title>
</head>
<body>

<div class="container">
    <div class="row py-3">
        <div class="col-3" id="sticky-sidebar">
            <div class="sticky-top">
                <div class="nav flex-column">
                    <a href="#_" class="nav-link">Link</a>
                    <a href="#_" class="nav-link">Link</a>
                    <a href="#_" class="nav-link">Link</a>
                    <a href="#_" class="nav-link">Link</a>
                    <a href="#_" class="nav-link">Link</a>
                </div>
            </div>
        </div>
        <div class="col" id="main">
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
                    <a href="nueva-derivacion?id=${paciente.id}">Generar nueva derivación</a>
                </div>
            </c:if>
        </div>
    </div>
</div>





</body>
</html>
