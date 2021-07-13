<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <%@ include file="../../../parts/meta.jsp" %>
    <title>Historial Derivaciones</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-10">
              <form action="filtrarDerivaciones" method="post">
                <label for="min">Desde</label>
                <input type="date" name="fechaMin" id="min">
                <label for="max">Hasta</label>
                <input type="date" name="fechaMax" id="max">
                <input type="submit" value="Buscar" class="btn-info">
              </form>
                                <c:forEach items="${derivaciones}" var="derivacion">
                                  <div>
                                  <div class="card" style="width: 18rem;">
                                  <div class="card-body">
                                  <h5 class="card-title">Derivacion del paciente: ${derivacion.getPaciente().getNombreCompleto()}</h5>
                                  <p class="card-text"> ${derivacion.getFechaDerivacion()} </p>
                                  <p class="card-text">Diagnostico: ${derivacion.getDiagnostico()}</p>
                                  <c:if test="${derivacion.getEstadoDerivacion().toString().equals('CANCELADA')}">
                                     <p class="card-text font-weight-bolder text-danger">Estado: cancelada</p>
                                  </c:if>
                                  <c:if test="${derivacion.getEstadoDerivacion().toString().equals('FINALIZADA')}">
                                     <p class="card-text font-weight-bolder text-danger">Estado: finalizada</p>
                                  </c:if>
                                  <!-- Button to Open the Modal -->
                                  <button type="button" class="btn btn-info" data-toggle="modal" data-target="#derivacion${derivacion.getId()}">
                                  Detalles
                                  </button>
                                  <!-- The Modal -->
                                  <div class="modal fade" id="derivacion${derivacion.getId()}">
                                  <div class="modal-dialog">
                                  <div class="modal-content">
                                  <!-- Modal Header -->
                                  <div class="modal-header">
                                  <h4 class="modal-title">Detalles de derivacion</h4>
                                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                                  </div>
                                  <!-- Modal body -->
                                  <div class="modal-body">
                                  <h4>Nombre de paciente: </h4>
                                  <span>${derivacion.getPaciente().getNombreCompleto()}</span>
                                  <h4>DNI: </h4>
                                  <span>${derivacion.getPaciente().getDocumento()}</span>
                                  <h4>Fecha nacimiento paciente: </h4>
                                  <span>${derivacion.getPaciente().getFechaNacimiento()}</span>
                                  <h4>Diagnostico:</h4>
                                  <span>${derivacion.getDiagnostico()}</span>
                                  <h4>Sector solicitado:</h4>
                                  <span>${derivacion.getParaQueSector()}</span>
                                  <h4>Cobertura:</h4>
                                  <span>${derivacion.getCobertura().getNombre()}</span>
                                  </div>
                                  <!-- Modal footer -->
                                  <div class="modal-footer">
                                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                  </div>
                                  </div>
                                  </div>
                                  </div>
                                  </div>
                                  </div>
                                  </div>
                                </c:forEach>
</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
