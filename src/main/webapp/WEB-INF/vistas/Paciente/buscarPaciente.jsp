<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Buscar Paciente</title>
</head>
<body>
            <!-- se agrega la columna menu -->
        <%@ include file="../../../parts/menu.jsp" %>
        <div class="col-10" id="main">
            <form action="ObtenerPaciente" method="post">
                <label for="documento">Numero de Documento del Paciente</label>
                <input name="documento" type="number" id="documento"/>
                <button type="submit">Buscar</button>
            </form>

            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">
                    <p>${error}</p>
                </div>
            </c:if>

            <c:if test="${not empty paciente}">
                <div>
                    <p>Paciente ${paciente.nombreCompleto}</p>
                    <p>${paciente.documento}</p>
                    <p>${paciente.fechaNacimiento}</p>
                    <a href="nueva-derivacion/${paciente.id}">Generar nueva derivación</a>
                </div>
            </c:if>
            <h2 class="text-center"> Derivaciones activas</h2>
            <div class="d-flex flex-wrap mb-3">
            <c:forEach items="${derivaciones}" var="derivacion">
                <div class="card m-2" style="width: 18rem;">
                    <div class="card-body">
                    	<button type="button" class="close" data-toggle="modal" data-target="#CancelarDerivacion${derivacion.getId() }">&times;</button>
                        <h5 class="card-title">Derivacion del paciente: ${derivacion.getPaciente().getNombreCompleto()}</h5>
                        <p class="card-text"> ${derivacion.getFechaDerivacion()} </p>
                        <p class="card-text">Diagnostico: ${derivacion.getDiagnostico()}</p>
                        <!-- Button to Open the Modal -->
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#derivacion${derivacion.getId()}">
                            Detalles
                        </button>
                                                <!-- The Modal -->
                                                <div class="modal fade" id="derivacion${derivacion.getId()}">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <!-- Modal Header -->
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Detalles de derivacion</h4>
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
                                                                <h4>Diagnostico:</h4>
                                                                <span>${derivacion.getDiagnostico()}</span>
                                                                <h4>Sector solicitado:</h4>
                                                                <span>${derivacion.getParaQueSector()}</span>
                                                                <h4>Cobertura:</h4>
                                                                <span>${derivacion.getCobertura().getNombre()}</span>
                                                            </div>
                                                            <!-- Modal footer -->
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                                            </div>
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

                        <c:if test="${derivacion.getEstadoDerivacion().toString().equals('ENTRASLADO')}">
                            <a href="ver-traslado/${derivacion.getId()}">
                                <button type="button" class="btn btn-success"> Ver Traslado </button></a>
                        </c:if>
                        
                    </div>
                </div>
            </c:forEach>
            </div>
        </div>

<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
