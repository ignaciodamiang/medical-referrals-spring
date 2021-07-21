<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Requerimientos Médicos</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
    <div class="col-12" id="main">
        <form action="./ModificarRequerimientosMedicos/${CentroMedico.getId()}" method="post">
            <h3 class="form-signin-heading text-center">Administración de Recursos Médicos para el Centro Médico ${CentroMedico.nombre} </h3>
            <hr class="colorgraph"><br>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="tomografo" name="tomografo" <c:if test="${Requerimientos.tomografo}">checked</c:if>>
                <label class="custom-control-label" for="tomografo">tomógrafo</label>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="traumatologoGuardia" name="traumatologoGuardia" <c:if test="${Requerimientos.traumatologoDeguardia}">checked</c:if>>
                <label class="custom-control-label" for="traumatologoGuardia">traumatólogo de guardia</label>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="cirujanoGuardia" name="cirujanoGuardia" <c:if test="${Requerimientos.cirujanoDeGuardia}">checked</c:if>>
                <label class="custom-control-label" for="cirujanoGuardia">cirujano de guardia</label>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="cardiologoGuardia" name="cardiologoGuardia" <c:if test="${Requerimientos.cardiologoSeGuardia}">checked</c:if>>
                <label class="custom-control-label" for="cardiologoGuardia">cardiólogo de guardia</label>
            </div>

            <input type="submit" value="Confirmar" class="form-control">
        </form>
    </div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
