<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Detalle Notificacion</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
    <table class="table table-bordered border-primary">
        <thead>
        <tr>
            <th scope="col">Notificación</th>
            <th scope="col">Detalle</th>
            <th scope="col">Traslado</th>
            <th scope="col">Derivación</th>
            <th scope="col">Fecha/Hora</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">${detalleNotificacion.notificacion.titulo}</th>
            <td>${detalleNotificacion.notificacion.mensaje}</td>
            <td><a href="../ver-traslado/${detalleNotificacion.getNotificacion().getTraslado().getDerivacion().getId()}">${detalleNotificacion.notificacion.traslado.codigo}</a></td>
            <td><a href="../ver-derivacion?id=${detalleNotificacion.notificacion.derivacion.id}">${detalleNotificacion.notificacion.derivacion.codigo}</a></td>
            <td>${detalleNotificacion.getNotificacion().getFecha()}</td>
        </tr>
        </tbody>
    </table>


</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
