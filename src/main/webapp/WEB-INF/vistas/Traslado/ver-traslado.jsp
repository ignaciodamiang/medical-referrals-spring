<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Traslado de derivacion</title>
</head>
<!-- se agrega la columna menu -->
<%@ include file="../../../parts/menu.jsp" %>
<div class="col-12" id="main">
    <!--  fin menu -->

<div class="container" style="margin-top: 6rem">

    <!-- Outer Row -->
    <div class="row justify-content-center">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Nombre paciente</th>
                <th scope="col">Centro medico</th>
                <th scope="col">Direccion destino</th>
                <th scope="col">Ubicacion de paciente</th>
                <th scope="col">Estado</th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${traslado.getDerivacion().getPaciente().getNombreCompleto()}</td>
                    <td>${traslado.getCentroMedico().getNombre()}</td>
                    <td>${traslado.getCentroMedico().getDireccion()}</td>
                    <td>${traslado.getDerivacion().getUbicacionPaciente()}</td>
                    <td>${traslado.getEstadoTraslado().toString()}</td>
                    <td><a href="../ver-derivacion?id=${traslado.getDerivacion().getId()}">
                        <button type="button" class="btn btn-success"> Volver </button></a></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div id="map" class="w-100 h-100">
    </div>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtr4ecOGJjwlxG3eXQeDCksZdMe2PNxBs&callback=initMap" type="text/javascript"></script>
    <script>
        // inicializar mapa
        function initMap() {
            // Setear obtener latitud y longitud del centro medico
            let mostrar = JSON.parse(`${coordenadas}`);
            let origen = JSON.parse(`${origen}`);
            let latitud = mostrar.candidates[0].geometry.location.lat;
            let longitud = mostrar.candidates[0].geometry.location.lng;
            const centroMedico = {
                lat: latitud,
                lng: longitud
            }
            const pos = {
                lat: origen.candidates[0].geometry.location.lat,
                lng: origen.candidates[0].geometry.location.lng
            }

            // Centramos el mapa en la ubicacion del centro medico
            const directionsRenderer = new google.maps.DirectionsRenderer();
            const directionsService = new google.maps.DirectionsService();
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 12,
                center: centroMedico,
            });
            console.log(latitud+','+longitud);

           // modificamos el icono que va a definir el centro medico para mostrar una imagen
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
        }
    </script>
    <!--  footer -->
</div>

    <%@ include file="../../../parts/footer.jsp" %>

    </div>
</html>