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
    <div id="traslados" style="width: 900px; height: 500px;"></div>
    <div id="solicitudes" style="width: 900px; height: 500px;"></div>

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
        <c:forEach items="${pacientesIngresados}" var="paciente">
        <tr>
            <td>${paciente.getFechaCreacion()}</td>
            <td>${paciente.getDerivacion().getPaciente().getNombre()}</td>
            <td>${paciente.getDerivacion().getPaciente().getDocumento()}</td>
            <td>${paciente.getDerivacion().getParaQueSector()}</td>
        </tr>
        </c:forEach>
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
            <c:forEach items="${pacientesIngresados}" var="paciente">
                <tr>
                    <td>${paciente.getFechaCreacion()}</td>
                    <td>${paciente.getDerivacion().getPaciente().getNombre()}</td>
                    <td>${paciente.getDerivacion().getPaciente().getDocumento()}</td>
                    <td><a href="ver-derivacion?id=${paciente.getDerivacion().getId()}">${paciente.getDerivacion().getCodigo()}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<%@ include file="../../../parts/footer.jsp" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['Traslado', 'Cantidad'],
            ['Cancelados',     ${cantidadTrasladosCancelados}],
            ['Finalizados',      ${cantidadTrasladosFinalizados}],
        ]);

        var options = {
            title: 'Traslados'
        };

        var chart = new google.visualization.PieChart(document.getElementById('traslados'));

        chart.draw(data, options);
    }

    function drawChart1() {

        var data = google.visualization.arrayToDataTable([
            ['Solicitudes', 'Cantidad'],
            ['Aceptadas',  ${cantidadSolicitudesAceptadas}],
            ['Rechazadas', ${cantidadSolicitudesRechazadas}],
        ]);

        var options = {
            title: 'Solicitudes Derivacion'
        };

        var chart = new google.visualization.PieChart(document.getElementById('solicitudes'));

        chart.draw(data, options);
    }

    function mostrarOcultar(id){
        switch(id){
            case 'ingresados':
                if(document.getElementById('ingresados').style.display == 'initial'){
                    return document.getElementById('ingresados').style.display ='none';
                }
                document.getElementById('ingresados').style.display='initial';
                document.getElementById('salidos').style.display='none';
                break;
            case 'salidos':
                if(document.getElementById('salidos').style.display == 'initial'){
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
