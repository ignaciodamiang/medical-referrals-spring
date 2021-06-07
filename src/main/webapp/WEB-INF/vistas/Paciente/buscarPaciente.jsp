<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <a href="nueva-derivacion/${paciente.id}">Generar nueva derivación</a>
                </div>
            </c:if>
            <c:forEach items="${derivaciones}" var="derivacion">
            <div>
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Derivacion del paciente: ${derivacion.getPaciente().getNombreCompleto()}</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>

            </div>
            </c:forEach>
        </div>
    </div>
    </div>

</body>
</html>
