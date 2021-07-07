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
    <div id="map" class="w-100 h-100">
    </div>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtr4ecOGJjwlxG3eXQeDCksZdMe2PNxBs&callback=initMap"        type="text/javascript"></script>
    <script>
        // inicializar mapa
        function initMap() {
            // Setear obtener latitud y longitud del centro medico
            let mostrar = JSON.parse(`${coordenadas}`);
            let latitud = mostrar.candidates[0].geometry.location.lat;
            let longitud = mostrar.candidates[0].geometry.location.lng;
            const centroMedico = {
                lat: latitud,
                lng: longitud
            }

            // Centramos el mapa en la ubicacion del centro medico
            const directionsRenderer = new google.maps.DirectionsRenderer();
            const directionsService = new google.maps.DirectionsService();
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 12,
                center: centroMedico,
            });
            console.log(latitud+','+longitud);

            var icon = {
                url: "https://image.flaticon.com/icons/png/512/504/504276.png", // url
                scaledSize: new google.maps.Size(35, 35), // scaled size
                origin: new google.maps.Point(0,0), // origin
                anchor: new google.maps.Point(0, 0) // anchor
            };

            // Creamos la marca de la ubicacion del centro medico
            const marker = new google.maps.Marker({
                position: centroMedico,
                map: map,
                icon: icon
            });

            // geolocalizamos la ubicacion del usuarip
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    (position) => {
                         const pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude,
                        };

                        directionsRenderer.setMap(map);

                        //funcion que calcula la ruta
                        directionsService
                            .route({
                                origin: pos ,
                                destination: centroMedico,
                                travelMode: google.maps.TravelMode.DRIVING,
                            })
                            .then((response) => {
                                directionsRenderer.setDirections(response);
                            })
                            .catch((e) => window.alert("Directions request failed due to " + e));
                    });
            }
        }
    </script>

<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
