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

<div class="container">
    <div class="justify-content-between d-flex mt-3">
        <h2 class="mt-4">Derivaciones</h2>
<%--        <a href="nueva-derivacion" style="text-decoration: none">--%>
<%--            <button type="button"  class="btn text-white mt-4" style="background-color:#d35400">Agregar Derivación</button>--%>
<%--        </a>--%>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
                ${message}
        </div>
    </c:if>

    <c:choose>
        <c:when test="${derivaciones.isEmpty()}">
            <h4 class="mt-4 text-center">No hay Derivaciones disponibles</h4>
        </c:when>
        <c:otherwise>
            <table class="table mt-4">
                <thead>
                <tr>
                    <th scope="col" class="text-center">Paciente</th>
                    <th scope="col" class="text-center">Cobertura</th>
                    <th scope="col" class="text-center">Finalizado</th>
                    <th scope="col" class="text-center">Diagnostico</th>
                    <th scope="col" class="text-center">Fecha</th>
                    <th scope="col" class="text-center">Sector</th>
                    <th scope="col" class="text-center" style="width: 21%">Acciones</th>
                    <th scope="col" class="text-center" style="width: 21%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${derivaciones}" var="derivacion">
                    <tr>
                        <th scope="row" class="text-center">${derivacion.paciente.nombreCompleto}</th>
                        <td class="text-center">${derivacion.cobertura.nombre}</td>
                        <td class="text-center">${derivacion.finalizada}</td>
                        <td class="text-center">${derivacion.diagnostico}</td>
                        <td class="text-center">${derivacion.fechaDerivacion}</td>
                        <td class="text-center">${derivacion.paraQueSector}</td>
<%--                        <td>--%>
<%--                            <div class="row justify-content-md-center">--%>
<%--                                <a href="modificar-derivacion/editar?id=${derivacion.id}"  class="btn btn-info  text-white"  role="button">Modificar</a>--%>
<%--                            </div>--%>
<%--                        </td>--%>
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="modificar-derivacion/editar?id=${derivacion.id}" modelAttribute="derivacion" class="btn btn-info  text-white"  role="button">Modificar</a>
                            </div>
                        </td>
                        <td>
                            <div class="row justify-content-md-center">
                                <a href="nueva-solicitud-derivacion/${derivacion.id}"class="btn btn-info mb-1 text-white"  role="button">Generar Solicitud</a>
                                <div class="col-sm">
                                    <a href="modificar-derivacion/editar?id=${derivacion.id}"  class="btn btn-info  text-white"  role="button">Modificar</a>
                                </div>
                                <div class="col-sm">
                                    <form:form action="eliminar-derivacion" method="post" modelAttribute="eliminarDerivacion">
                                        <form:input path="id" type="hidden" value="${derivacion.id}" />
                                        <input type="submit" value="Eliminar" class="btn btn-danger">
                                    </form:form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    </div>
</body>
</html>
