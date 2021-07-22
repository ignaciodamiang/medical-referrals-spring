<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Gráfica</title>
</head>
<body>
<%@ include file="../../../parts/menu.jsp" %>


<div class="col-12" id="main">
    <form action="filtrarGraficas" method="post">
        <label for="min">Desde</label>
        <input class="form-control mb-1 w-25" type="date" name="fechaMin" id="min">
        <label for="max">Hasta</label>
        <input class="form-control mb-1 w-25" type="date" name="fechaMax" id="max">
        <button type="submit" class="btn btn-primary w-25">Buscar</button>
    </form>
    <div class="col-6">
    <div id="traslados" style="width: 900px; height: 500px;"></div>
    </div>
    <div class="col-6">
    <div id="solicitudes" style="width: 900px; height: 500px;"></div>
    </div>

    <p class="d-flex justify-content-around">
        <button class="btn btn-primary" onclick="mostrarOcultar('ingresados')">Pacientes Ingresados</button>
        <button class="btn btn-primary" onclick="mostrarOcultar('salidos')">Pacientes que Salieron</button>
    </p>
    <div id="ingresados">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Fecha</th>
            <th scope="col">Paciente</th>
            <th scope="col">Documento</th>
            <th scope="col">Sector</th>
        </tr>
        </thead>
        <tbody>
<%--        <c:forEach items="${pacientesIngresados}" var="paciente">--%>
<%--        <tr>--%>
<%--            <td>${paciente.getFechaCreacion()}</td>--%>
<%--            <td>${paciente.getDerivacion().getPaciente().getNombre()}</td>--%>
<%--            <td>${paciente.getDerivacion().getPaciente().getDocumento()}</td>--%>
<%--            <td>${paciente.getDerivacion().getParaQueSector()}</td>--%>
<%--        </tr>--%>
<%--        </c:forEach>--%>
        </tbody>
    </table>
    </div>
    <div id="salidos">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Fecha</th>
                <th scope="col">Paciente</th>
                <th scope="col">Documento</th>
                <th scope="col">Derivacion</th>
            </tr>
            </thead>
            <tbody>
<%--            <c:forEach items="${pacientesIngresados}" var="paciente">--%>
<%--                <tr>--%>
<%--                    <td>${paciente.getFechaCreacion()}</td>--%>
<%--                    <td>${paciente.getDerivacion().getPaciente().getNombre()}</td>--%>
<%--                    <td>${paciente.getDerivacion().getPaciente().getDocumento()}</td>--%>
<%--                    <td><a href="ver-derivacion?id=${paciente.getDerivacion().getId()}">${paciente.getDerivacion().getCodigo()}</a></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>


<%@ include file="../../../parts/footer.jsp" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);;

    function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['Traslado', 'Cantidad'],
            ['Cancelados', ${cantidadTrasladosCancelados}],
            ['Finalizados', ${cantidadTrasladosFinalizados}],
        ]);

        var options = {
            title: 'Traslados'
        };

        var chart = new google.visualization.PieChart(document.getElementById('traslados'));

        chart.draw(data, options);

        var data1 = google.visualization.arrayToDataTable([
            ['Solicitudes', 'Cantidad'],
            ['Aceptadas',  ${cantidadSolicitudesAceptadas}],
            ['Rechazadas', ${cantidadSolicitudesRechazadas}],
        ]);

        var options1 = {
            title: 'Solicitudes Derivacion'
        };

        var chart1 = new google.visualization.PieChart(document.getElementById('solicitudes'));

        chart1.draw(data1, options1);
    }

    function mostrarOcultar(id){
        switch(id){
            case 'ingresados':
                if(document.getElementById('salidos').style.display == 'initial'){
                    return document.getElementById('ingresados').style.display ='none';
                }
                document.getElementById('ingresados').style.display='initial';
                document.getElementById('salidos').style.display='none';
                break;
            case 'salidos':
                if(document.getElementById('ingresados').style.display == 'initial'){
                    return document.getElementById('salidos').style.display ='none';
                }
                document.getElementById('salidos').style.display='initial';
                document.getElementById('ingresados').style.display='none';
                break;
            default:
                document.getElementById('ingresados').style.display='none';
                document.getElementById('salidos').style.display='none';
                break;
        }
    }
</script>
</body>
</html>
