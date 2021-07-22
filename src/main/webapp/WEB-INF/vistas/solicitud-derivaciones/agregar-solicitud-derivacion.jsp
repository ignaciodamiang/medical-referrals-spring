<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Solicitud derivaciones</title>
</head>
<body>

<!-- se agrega la columna menu -->
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
    <%--   fin menu  --%>
<div class="d-flex">
    <div class="col-lg-6 justify-content-center mx-auto">
        <form action="../agregar-solicitud-derivacion" method="post" class="mt-4">
            <h3 class="form-signin-heading text-center">Crear Solicitud para derivar al Paciente ${derivaciones.paciente.nombreCompleto}</h3>
            <hr class="colorgraph"><br>

            <input type="number" value="${derivaciones.id}" name="idDerivacion" hidden>

            <div class="form-group">
                <label for="centroMedico">Centro Medico</label><br>
                <select name="centroMedico" id="centroMedico" class="form-control">
                    <c:forEach items="${centrosMedicos}" var="centroMedico">
                        <option value="${centroMedico.centroMedico.id}">${centroMedico.centroMedico.nombre}  ${centroMedico.distancia}Km</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="descripcion">Mensaje de la solicitud</label><br>
                <textarea name="descripcion" class="form-control" id="descripcion" placeholder="Escriba la descripcion de la solicitud" rows="4" cols="50"></textarea>
            </div>

            <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Generar Solicitud de derivación</button>
        </form>
    </div>
</div>
</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
