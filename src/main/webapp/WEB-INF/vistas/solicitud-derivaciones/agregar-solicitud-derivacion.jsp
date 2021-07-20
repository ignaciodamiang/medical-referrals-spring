<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link href="${css}/css/style.css" rel="stylesheet"/>
    <title>Solicitud derivaciones</title>
</head>
<body>
<div class="d-flex">
    <div class="col-lg-6 justify-content-center mx-auto">
        <form action="../agregar-solicitud-derivacion" method="post" class="mt-4">
            <h3 class="form-signin-heading text-center">Crear Solicitud para derivar al Paciente ${derivaciones.paciente.nombreCompleto}</h3>
            <hr class="colorgraph"><br>

            <input type="number" value="${derivaciones.id}" name="idDerivacion" hidden>

            <div class="form-group">
                <label for="centroMedico">Centro Medico</label>
                <select name="centroMedico" id="centroMedico" >
                    <c:forEach items="${centrosMedicos}" var="centroMedico">
                        <option value="${centroMedico.centroMedico.id}">${centroMedico.centroMedico.nombre}  ${centroMedico.distancia}Km</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="descripcion">Mensaje de la solicitud</label>
                <textarea name="descripcion" id="descripcion" placeholder="Escriba la descripcion de la solicitud" rows="4" cols="50"></textarea>
            </div>

            <button class="btn btn-lg btn-info btn-block" Type="Submit"/>Generar Solicitud de derivación</button>
        </form>
    </div>
</div>
</body>
</html>
