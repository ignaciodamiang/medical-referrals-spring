<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Notificaciones</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-10" id="main">
<h2 class="text-center"> Notificaciones</h2>
    <div class="d-flex flex-wrap mb-3">
<c:forEach items="${notificaciones}" var="notificacion">
    <div class="card m-2" style="width: 18rem;">
        <div class="card-body">
            <a href="detalleNotificacion/${notificacion.notificacion.id}"><h5 class="card-title">Derivacion del paciente: ${notificacion.notificacion.titulo}</h5></a>
            <p class="card-text"> ${notificacion.notificacion.fecha} </p>
        </div>
    </div>
</c:forEach>
    </div>
</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
