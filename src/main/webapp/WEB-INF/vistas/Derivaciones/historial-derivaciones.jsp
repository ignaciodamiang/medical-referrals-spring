<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <%@ include file="../../../parts/meta.jsp" %>
  <title>Historial Derivaciones Derivador</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
  <form action="filtrarDerivaciones" method="post">
    <label for="min">Desde</label>
    <input type="date" name="fechaMin" id="min">
    <label for="max">Hasta</label>
    <input type="date" name="fechaMax" id="max">
    <input type="submit" value="Buscar" class="btn-info">
  </form>
  <table class="table table-hover table-striped table-bordered border-primary">
    <thead>
    <tr>
      <th scope="col">Código</th>
      <th scope="col">Fecha</th>
      <th scope="col">Nombre completo</th>
      <th scope="col">Documento</th>
      <th scope="col">Diagnóstico</th>
      <th scope="col">Estado</th>
      <th scope="col">Complejidad</th>
      <th scope="col">Cobertura</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${derivaciones}" var="derivacion">
      <tr>
        <th scope="row">${derivacion.getCodigo()}</th>
        <td>${derivacion.getFechaDerivacion()}</td>
        <td>${derivacion.getPaciente().getNombreCompleto()}</td>
        <td>${derivacion.getPaciente().getDocumento()}</td>
        <td>${derivacion.getDiagnostico()}</td>
        <c:if test="${derivacion.getEstadoDerivacion().toString().equals('CANCELADA')}">
          <td>Cancelada</td>
        </c:if>
        <c:if test="${derivacion.getEstadoDerivacion().toString().equals('FINALIZADA')}">
          <td>Finalizada</td>
        </c:if>
        <td>${derivacion.getParaQueSector()}</td>
        <td>${derivacion.getCobertura().getNombre()}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
