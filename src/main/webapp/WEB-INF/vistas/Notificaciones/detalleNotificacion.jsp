<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Detalle Notificacion</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-10" id="main">
    <h5 class="title-info">${detalleNotificacion.notificacion.titulo}</h5>
    <p class="lead">${detalleNotificacion.notificacion.mensaje}</p>
</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
