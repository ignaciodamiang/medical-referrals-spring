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
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <title>Derivaciones</title>
</head>
<body>
    <div class="d-flex">
        <div class="col-lg-6 justify-content-center mx-auto">
            <c:choose>
        <c:when test="${rol!='Administrativo'}"><form:form action="../agregar-derivacion" method="post" modelAttribute="derivacion" class="mt-4">

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
                                <label for="ubicacionPaciente">Ubicación del Paciente</label>
                                <input type="text" id="ubicacionPaciente" name="ubicacionPaciente" class="form-control">
                            </div>
                            <div id="map" class="w-50 h-50"></div>
                            <div class="form-group">
                                <label>Urgente</label>
                                <label for="si">Si</label>
                                <input type="radio" name="urgente" id="si" value="true">
                                <label for="no">No</label>
                                <input type="radio" name="urgente" id="no" value="false">
                            </div>

                            <input type="number" name="idPaciente" value="${paciente.getId()}"hidden>

                            <div class="form-group">
                                <label>Requerimientos medicos necesarios</label>
                                <label for="tomografo">tomógrafo</label>
                                <input type="checkbox" name="tomografo" id="tomografo">
                                <label for="traumatologoGuardia">traumatólogo de guardia</label>
                                <input type="checkbox" name="traumatologoGuardia" id="traumatologoGuardia">
                                <label for="cirujanoGuardia">cirujano de guardia</label>
                                <input type="checkbox" name="cirujanoGuardia" id="cirujanoGuardia">
                                <label for="cardiologoGuardia">cardiólogo de guardia</label>
                                <input type="checkbox" name="cardiologoGuardia" id="cardiologoGuardia">
                            </div>

                            <button class="btn btn-lg btn-info btn-block" Type="Submit"/>Crear Derivacion</button>
                        </form:form>
        </c:when>
            <c:otherwise>
                                <form:form action="../agregar-derivacion-centro-medico" method="post" modelAttribute="derivacion" class="mt-4">
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
                                    <label for="ubicacionPaciente">Ubicación del Paciente</label>
                                    <input type="text" value="${direccionCentroMedico}" id="ubicacionPaciente" name="ubicacionPaciente" class="form-control" readonly>
                                </div>
                                <div id="map" class="w-50 h-50"></div>
                                <div class="form-group">
                                    <label>Urgente</label>
                                    <label for="si">Si</label>
                                    <input type="radio" name="urgente" id="si" value="true">
                                    <label for="no">No</label>
                                    <input type="radio" name="urgente" id="no" value="false">
                                </div>

                                <input type="number" name="idPaciente" value="${paciente.getId()}"hidden>

                                <div class="form-group">
                                    <label>Requerimientos medicos necesarios</label>
                                    <label for="tomografo">tomógrafo</label>
                                    <input type="checkbox" name="tomografo" id="tomografo">
                                    <label for="traumatologoGuardia">traumatólogo de guardia</label>
                                    <input type="checkbox" name="traumatologoGuardia" id="traumatologoGuardia">
                                    <label for="cirujanoGuardia">cirujano de guardia</label>
                                    <input type="checkbox" name="cirujanoGuardia" id="cirujanoGuardia">
                                    <label for="cardiologoGuardia">cardiólogo de guardia</label>
                                    <input type="checkbox" name="cardiologoGuardia" id="cardiologoGuardia">
                                </div>

                                <button class="btn btn-lg btn-info btn-block" Type="Submit"/>Crear Derivacion</button>
                            </form:form>
            </c:otherwise>
            </c:choose>

    </div>
    </div>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtr4ecOGJjwlxG3eXQeDCksZdMe2PNxBs&callback=initAutocomplete&libraries=places&v=weekly" type="text/javascript"></script>
    <script>
        function initAutocomplete() {
            //inicializamos el mapa y ponemos como punto central la universidad de la Matanza
            const map = new google.maps.Map(document.getElementById("map"), {
                center: {lat: -34.670574646949994, lng: -58.56302613429685 },
                zoom: 13,
                mapTypeId: "roadmap",
            });
            // Creamos la caja de busqueda de direcciones.
            const input = document.getElementById("ubicacionPaciente");
            const searchBox = new google.maps.places.SearchBox(input);
            // cada ver que se coloca una direccion el mapa muestra dicha ubicacion.
            map.addListener("bounds_changed", () => {
                searchBox.setBounds(map.getBounds());
            });
            let markers = [];
            // Cuando el usuario escribe sobre el textbox le aparecen varias direcciones para autocompletar
            searchBox.addListener("places_changed", () => {
                const places = searchBox.getPlaces();

                if (places.length == 0) {
                    return;
                }
                // Se borran las marcas anteriores cuando la direccion cambia.
                markers.forEach((marker) => {
                    marker.setMap(null);
                });
                markers = [];
                // obtenemos la ubicacion de cada direccion.
                const bounds = new google.maps.LatLngBounds();
                places.forEach((place) => {
                    if (!place.geometry || !place.geometry.location) {
                        console.log("Returned place contains no geometry");
                        return;
                    }
                    const icon = {
                        url: "https://image.flaticon.com/icons/png/512/387/387585.png",
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(25, 25),
                    };
                    // se crea una marca para cada ubicacion.
                    markers.push(
                        new google.maps.Marker({
                            map,
                            icon,
                            title: place.name,
                            position: place.geometry.location,
                        })
                    );

                    if (place.geometry.viewport) {
                        // Only geocodes have viewport.
                        bounds.union(place.geometry.viewport);
                    } else {
                        bounds.extend(place.geometry.location);
                    }
                });
                map.fitBounds(bounds);
            });
        }

    </script>
    <%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
