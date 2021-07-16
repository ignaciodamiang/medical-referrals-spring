<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Ver Derivacion</title>
</head>
<body>
<!-- se agrega la columna menu -->
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-10" id="main">
    <!--  fin menu -->
    <div class="row p-3  d-flex">
        <div class="d-flex flex-grow-1">
            <form class="form-group d-flex" method="get" action="./ver-derivacion">
                <input class="form-control" name="idDerivacion" type="text" placeholder="Ingrese id de derivacion">
                <input type="input" class="btn btn-primary w-25 ml-4" value="Buscar">
            </form>
        </div>
        <div class="px-3">
            <button class="btn btn-primary"> Cancelar derivacion</button>
        </div>
        <div class="px-3">
            <button class="btn btn-primary"> Generar solicitud derivacion</button>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="row">
                <div class="col m-2">
                    <div class="row m-1"> <strong>Id derivacion: </strong><p>${derivacion.id}</p></div>
                    <div class="row m-1"> <strong>Sector: </strong><p>${alalla.getId()}</p></div>
                    <div class="row m-1"> <strong>requisitos: </strong><p>trauma <br> adadadaddas<br> sadasdas </p></div>
                </div>
                <div class="col m-2">
                    <div class="row m-1"> <strong>Id derivacion: </strong><p>${derivacion.getId()}</p></div>
                    <div class="row m-1"> <strong>Idallasas: </strong><p>${alalla.getId()}</p></div>
                </div>
                <div class="col m-2">
                    <div class="row m-1"> <strong>Id derivacion: </strong><p>${derivacion.getId()}</p></div>
                    <div class="row m-1"> <strong>Idallasas: </strong><p>${alalla.getId()}</p></div>
                </div>
            </div>
        </div>
    </div>
    <!--  agregar div con rows -->
    <div class="row">
        <div class="col m-2">
            <strong>Diagnostico: </strong> <p>${derivacion.diagnostico}</p>
        </div>
    </div>
    <!-- <div class="row bg-warning"> -->
    <p class="d-flex justify-content-around">
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#multiCollapseExample1" aria-expanded="false" aria-controls="multiCollapseExample1">Toggle first element</button>
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#multiCollapseExample2" aria-expanded="false" aria-controls="multiCollapseExample2">Toggle second element</button>
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#multiCollapseExample3" aria-expanded="false" aria-controls="multiCollapseExample2">Toggle third element</button>

    </p>
    <div class="row">
        <div class="col">
            <div class="collapse multi-collapse" id="multiCollapseExample1">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td colspan="2">Larry the Bird</td>
                        <td>@twitter</td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="col">
            <div class="collapse multi-collapse" id="multiCollapseExample2">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td colspan="2">Larry the Bird</td>
                        <td>@twitter</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col">
            <div class="collapse multi-collapse" id="multiCollapseExample3">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First</th>
                        <th scope="col">Last</th>
                        <th scope="col">Handle</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td colspan="2">Larry the Bird</td>
                        <td>@twitter</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- </div> -->
    </div>
    <div class="row">
        <div class="col m-2">
            <strong>Diagnostico: </strong> <p>${derivacion.getDiagnostico()}</p>
        </div>
    </div>
    <div class="row">
        <div class="col m-2">
            <strong>Diagnostico: </strong> <p>${derivacion.getDiagnostico()}</p>
        </div>
    </div>
</div>
<!--  fin de divs con rows -->
    <!--  footer -->
</div>

<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>