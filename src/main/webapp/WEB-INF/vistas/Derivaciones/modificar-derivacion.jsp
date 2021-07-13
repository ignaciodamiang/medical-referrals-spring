<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link href="${css}/css/style.css" rel="stylesheet"/>
    <title>Modificar Derivicion</title>
</head>
<body>
<div class="container">
    <div class="col-md-10 mt-5 mb-5 mx-auto">
        <form:form action="editar" modelAttribute="derivacion" method="POST">
            <h3 class="form-signin-heading text-center">Modificar Derivacion ${derivacion.paciente.nombreCompleto}</h3>
            <hr class="colorgraph"><br>

            <div class="form-group">
                <label for="id">Id</label>
                <form:input path="id" type="text" id="id" class="form-control" readonly="true"/>
            </div>

            <div class="form-group">
                <label for="diagnostico">Diagnostico</label>
                <form:input path="diagnostico" type="text" id="diagnostico" class="form-control"  />
            </div>

            <div class="form-group">
                <label for="sector">Sector</label>
                <form:input path="paraQueSector" type="text" id="sector" class="form-control"/>
            </div>


            <div class="form-group">
                <label for="cobertura">Cobertura</label>
                <form:input path="cobertura.id" type="text" id="cobertura" value="1" class="form-control" />
            </div>

            <div class="form-group">
                <label for="paciente">Paciente</label>
                <form:input path="paciente.id" type="text" id="paciente" class="form-control" value="1" />
            </div>

            <form:button class="btn btn-info btn-user btn-block text-white">Modificar</form:button>
        </form:form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>

