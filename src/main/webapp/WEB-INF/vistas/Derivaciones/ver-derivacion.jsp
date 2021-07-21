<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <form class="form-group d-flex" method="get" action="/proyecto_derivaciones_war_exploded/ver-derivacion/">
                <input class="form-control" name="id" type="text" placeholder="Ingrese id de derivacion">
                <input type="submit" class="btn btn-primary w-25 ml-4" value="Buscar">
            </form>
        </div>
        <c:if test="${rol =='Derivador' || rol =='Solicitador'}">
            <div class="px-3">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#CancelarDerivacion${derivacion.getId() }">Cancelar derivacion</button>
            </div>
        </c:if>
        <c:if test="${rol =='Derivador' && derivacion.getEstadoDerivacion().toString().equals('ENBUSQUEDA')}">
            <div class="px-3">
                <a href="nueva-solicitud-derivacion/${derivacion.id} "class="btn btn-primary">Generar Solicitud</a>
            </div>
        </c:if>

    </div>
    <div class="row">
        <div class="col">
            <h3>Derivacion: ${derivacion.getCodigo()}</h3>
            <div class="row">
                <div class="col m-2">
                    <div class="row m-1"> <strong>Sector: </strong><p> ${derivacion.getParaQueSector()}</p></div>
                    <div class="row m-1"> <strong>requisitos: </strong>
                        <c:choose>
                            <c:when test="${derivacion.getRequerimientosMedicos().getTomografo()}">
                                - TOMOGRAFO <br>
                            </c:when>
                            <c:when test="${derivacion.getRequerimientosMedicos().getTraumatologoDeguardia()}">
                                - TRAUMATOLOGO DE GUARDIA <br>
                            </c:when>
                            <c:when test="${derivacion.getRequerimientosMedicos().getCirujanoDeGuardia()}">
                                - CIRUJANO DE GUARDIA <br>
                            </c:when>
                            <c:when test="${derivacion.getRequerimientosMedicos().getCardiologoSeGuardia()}">
                                - CARDIOLOGO DE GUARDIA <br>
                            </c:when>
                            <c:otherwise>
                                - NINGUNO <br>
                            </c:otherwise>
                        </c:choose>

                        </div>
                </div>
                <div class="col m-2">
                    <div class="row m-1"> <strong>Paciente: </strong>
                        <a style="color: blue" data-toggle="modal" data-target="#paciente${derivacion.getPaciente().getId()}">${derivacion.getPaciente().getNombreCompleto()}</a>
                    </div>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="paciente${derivacion.getPaciente().getId()}">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <!-- Modal Header -->
                                                                    <div class="modal-header">
                                                                        <img src="/proyecto_derivaciones_war_exploded/img/pacientes/${derivacion.getPaciente().getFoto()}" class="rounded-circle img-fluid img-thumbnail" alt="${derivacion.getPaciente().getFoto()}" width="80">
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <!-- Modal body -->
                                                                    <div class="modal-body">
                                                                        <h4>Nombre de paciente: </h4>
                                                                        <span>${derivacion.getPaciente().getNombreCompleto()}</span>
                                                                        <h4>DNI: </h4>
                                                                        <span>${derivacion.getPaciente().getDocumento()}</span>
                                                                        <h4>Fecha nacimiento paciente: </h4>
                                                                        <span>${derivacion.getPaciente().getFechaNacimiento()}</span>
                                                                    </div>
                                                                    <!-- Modal footer -->
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                    <div class="row m-1"> <strong>Urgente: </strong>
                        <c:choose>
                        <c:when test="${derivacion.getUrgente()}">
                        <p class="text-center px-2" style="background: darkred;color: white;">URGENTE</p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-center"> NO </p>
                        </c:otherwise>
                        </c:choose></div>
                    </div>
                <div class="col m-2">
                    <div class="row m-1"> <strong>Estado: </strong><p>${derivacion.getEstadoDerivacion()}</p></div>
                    <div class="row m-1"> <strong>Fecha generacion: </strong><p>${derivacion.getFechaDerivacion()}</p></div>
                    <div class="row m-1"> <strong>Cobertura: </strong><p>${derivacion.getCobertura().getNombre()}</p></div>
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
        <button class="btn btn-primary" onclick="mostrarOcultar('solicitudes')">Solicitudes de derivacion</button>
        <button class="btn btn-primary" onclick="mostrarOcultar('registros')">Registro</button>
        <button class="btn btn-primary" onclick="mostrarOcultar('adjuntos')">Adjuntos</button>

    </p>
    <div class="">
        <div class="row" id="solicitudes">
            <c:choose>
                <c:when test="${listaSolicitudesDerivaciones.isEmpty()}">
                    <p class="mt-4 text-center">No hay Solicitudes disponibles</p>
                </c:when>
                <c:otherwise>
                    <table class="table table-striped mt-4">
                        <thead>
                        <tr>
                            <th scope="col">Id solicitud</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Centro Medico</th>
                            <th scope="col">Aceptado</th>
                            <th scope="col">Confirmado</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Urgencia</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listaSolicitudesDerivaciones}" var="solicitud">
                            <tr>
                                <td><a href="ver-solicitud-derivacion/${solicitud.getId()}">${solicitud.getCodigo()}</a></td>
                                <td>${solicitud.getDerivacion().getEstadoDerivacion().toString()}</td>
                                <td>${solicitud.getCentroMedico().getNombre()}</td>
                                <td>${solicitud.getAceptado()}</td>
                                <td>${solicitud.getConfirmado()}</td>
                                <td>${solicitud.getFechaCreacion().toLocaleString()}</td>
                                <c:choose>
                                    <c:when test="${solicitud.getDerivacion().getUrgente()}">
                                        <td class="text-center" style="background: darkred;color: white;">URGENTE</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-center">---</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
<%--            ${path}--%>
<%--            <img src="/proyecto_derivaciones_war_exploded/img/pacientes/${derivacion.getPaciente().getFoto()}">--%>
<%--            <img src="proyecto-derivaciones\out\artifacts\proyecto_derivaciones_war_exploded\">--%>
        </div>
        <div class="row" id="registros"> <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Fecha</th>
                <th scope="col">Asunto</th>
                <th scope="col">Usuario</th>
                <th scope="col">Evento</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${comentarios}" var="comentario">
                <tr>
                    <td>${comentario.getFechaCreacion()}</td>
                    <td>${comentario.getAsunto()}</td>
                    <td>${comentario.getAutor().getEmail()}</td>
                    <td>${comentario.getMensaje()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table></div>
        <div class="row" id="adjuntos">
            <button class="btn-info" data-toggle="modal" data-target="#derivacion${derivacion.getId()}">Agregar Adjunto</button>


                                                <div class="modal fade" id="derivacion${derivacion.getId()}">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <!-- Modal Header -->
                                                            <div class="modal-header">
                                                                <h3>Adjuntar Archivo</h3>
                                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                            </div>
                                                            <!-- Modal body -->
                                                            <div class="modal-body">
                                                                <form action="adjuntar-archivo-derivacion/${derivacion.getId()}" method="post" enctype="multipart/form-data">
                                                                    <div class="form-group">
                                                                        <input type="text" id="titulo" name="titulo" placeholder="Titulo del adjunto...">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <input type="file" id="adjunto" name="adjunto" accept="image/png, image/jpeg">
                                                                    </div>
                                                            </div>
                                                            <!-- Modal footer -->
                                                            <div class="modal-footer">
                                                                <input type="submit"  class="btn-success" value="Subir Archivo">
                                                                </form>
                                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
            <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Fecha</th>
                <th scope="col">Adjunto</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${ empty adjuntos}">
                    <tr>
                        <td> </td>
                        <td>Esta derivacion no posee adjuntos</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${adjuntos}" var="adjunto">
                        <tr>
                            <td>${adjunto.getFechaCreacion()}</td>
                            <td><a href="${path}${adjunto.getImgPath()}" download="${adjunto.getImgPath()}">${adjunto.getTitulo()}</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table></div>
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
<div class="modal fade" id="CancelarDerivacion${derivacion.getId()}">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h5>Cancelar derivación</h5>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <form action="cancelar-derivacion/${derivacion.getId() }" method="post">
                    <label>Motivo:</label>
                    <textarea name="mensaje" rows="5" cols="50"></textarea>
                    <button type="submit" class="btn btn-danger">Confirmar anulacion</button>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<!--  fin de divs con rows -->
    <!--  footer -->
</div>

<%@ include file="../../../parts/footer.jsp" %>
<script>
    function mostrarOcultar(id){
        switch(id){
            case 'solicitudes':
                if(document.getElementById('solicitudes').style.display == 'initial'){
                    return document.getElementById('solicitudes').style.display ='none';
                }
                document.getElementById('solicitudes').style.display='initial';
                document.getElementById('registros').style.display='none';
                document.getElementById('adjuntos').style.display='none';
                break;
            case 'registros':
                if(document.getElementById('registros').style.display == 'initial'){
                    return document.getElementById('registros').style.display ='none';
                }
                document.getElementById('registros').style.display='initial';
                document.getElementById('solicitudes').style.display='none';
                document.getElementById('adjuntos').style.display='none';
                break;
            case 'adjuntos':
                if(document.getElementById('adjuntos').style.display == 'initial'){
                    return document.getElementById('adjuntos').style.display ='none';
                }
                document.getElementById('adjuntos').style.display='initial';
                document.getElementById('solicitudes').style.display='none';
                document.getElementById('registros').style.display='none';
                break;
            default:
                document.getElementById('solicitudes').style.display='none';
                document.getElementById('registros').style.display='none';
                document.getElementById('adjuntos').style.display='none';
                break;
        }

    }
</script>
</body>
</html>