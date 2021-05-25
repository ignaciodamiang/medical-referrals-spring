<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class = "container">
    <table class="table">



        <thead>
        <tr>
            <th scope="col">Derivacion</th>
            <th scope="col">Centro Medico</th>
            <th scope="col">Aceptado</th>
            <th scope="col">Confirmado</th>
            <th scope="col">Fecha</th>
        </tr>
        </thead>
        <tbody>
<c:forEach items="${listaSolicitudesDerivaciones}" var="solicitud">
        <tr>
            <td>${solicitud.getDerivacion().getEstado()}</td>
            <td>${solicitud.getCentroMedico().getNombre()}</td>
            <td>${solicitud.getAceptado()}</td>
            <td>${solicitud.getConfirmado()}</td>
            <td>${solicitud.getFechaCreacion().toLocaleString()}</td>
        </tr>
</c:forEach>
        </tbody>
    </table>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>

