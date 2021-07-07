<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Traslado de derivacion</title>
</head>
<body class="bg-gradient-primary">

<div class="container" style="margin-top: 6rem">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">
            <div class="card o-hidden border-0 shadow-lg">
                <div class="card-body p-0">
                    <h1>${traslado.getDerivacion().getPaciente().getNombreCompleto()}</h1>
                    <h1>${traslado.getCentroMedico().getNombre()}</h1>
                    <h1>${traslado.getDireccionOrigen()}</h1>
                    <h1>${traslado.getCentroMedico().getDireccion()}</h1>
                    <a href="../BuscarPaciente">
                        <button type="button" class="btn btn-success"> Volver </button></a>
                </div>
            </div>
        </div>

    </div>
    <div id="map" class="w-50 h-50">
    </div>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtr4ecOGJjwlxG3eXQeDCksZdMe2PNxBs&callback=initMap"        type="text/javascript"></script>
    <script>
        // Initialize and add the map
        function initMap() {
            // The location of Uluru
            let mostrar = JSON.parse(`${coordenadas}`);
            let latitud = mostrar.candidates[0].geometry.location.lat;
            let longitud = mostrar.candidates[0].geometry.location.lng;
            const centroMedico = {
                lat: latitud,
                lng: longitud
            }
            // The map, centered at Uluru
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 12,
                center: centroMedico,
            });
            console.log(latitud+','+longitud);
            // The marker, positioned at Uluru
            const marker = new google.maps.Marker({
                position: centroMedico,
                map: map,
            });
        }
    </script>

<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
