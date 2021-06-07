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
    <link  href="${css}/style.css"  rel="stylesheet"/>
    <title>Derivaciones</title>
</head>
<body>
    <div class="d-flex">
        <div class="col-lg-6 justify-content-center mx-auto">
        <form:form action="../agregar-derivacion" method="post" modelAttribute="derivacion" class="mt-4">
            <h3 class="form-signin-heading text-center">Crear Derivacion para el Paciente ${paciente.nombreCompleto}</h3>
            <hr class="colorgraph"><br>


            <div class="form-group">
                <label for="diagnostico">Diagnostico</label>
                <form:input path="diagnostico" type="text" id="diagnostico" class="form-control"  />
            </div>

            <div class="form-group">
                <label for="cobertura">Sector</label>
                <form:select id="sector" path="paraQueSector" class="form-control">
                    <form:options items="${sectores}"/>
                </form:select>

            </div>

            <div class="form-group">
                <label for="cobertura">Cobertura</label>
                <form:select id="cobertura" path="cobertura.id" class="form-control">
                    <form:options items="${coberturas}" itemLabel="nombre" itemValue="id"/>
                </form:select>

            </div>
            <div class="form-group">
                <label>Urgente</label>
                <label for="si">Si</label>
                <input type="radio" name="urgente" id="si" value="true">
                <label for="no">No</label>
                <input type="radio" name="urgente" id="no" value="false">
            </div>

            <input type="number" name="idPaciente" value="${paciente.getId()}"hidden>

            <button class="btn btn-lg btn-info btn-block" Type="Submit"/>Crear Derivacion</button>
        </form:form>
    </div>
    </div>
</body>
</html>
