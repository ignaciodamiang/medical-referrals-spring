<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Notificaciones</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
<h2 class="text-center"> Notificaciones</h2>
    <div class="d-flex flex-wrap mb-3">
        <table class="table table-hover table-bordered border-primary">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Título</th>
                <th scope="col">Mensaje</th>
                <th scope="col">Fecha</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${notificaciones}" var="notificacion">
                <c:if test="${notificacion.getLeido().equals(false)}">
                <tr class="table-danger">
                    <th scope="row">${notificacion.notificacion.id}</th>
                    <td><a href="detalleNotificacion/${notificacion.id}">${notificacion.notificacion.titulo}</a></td>
                    <td scope="row">${notificacion.notificacion.mensaje}</td>
                    <td scope="row">${notificacion.notificacion.fecha}</td>
                </tr>
                </c:if>
            <c:if test="${notificacion.getLeido().equals(true)}">
                    <tr class="table-success">
                        <th scope="row">${notificacion.notificacion.id}</th>
                        <td><a href="detalleNotificacion/${notificacion.id}">${notificacion.notificacion.titulo}</a></td>
                        <td scope="row">${notificacion.notificacion.mensaje}</td>
                        <td scope="row">${notificacion.notificacion.fecha}</td>
                    </tr>
            </c:if>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
