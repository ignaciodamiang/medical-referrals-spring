<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="../../../parts/meta.jsp" %>
    <title>Traslados en Curso</title>
</head>
<body>
    <c:forEach items="${traslados}" var="traslado">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Traslado del Paciente: ${traslado.derivacion.paciente.nombreCompleto}</h5>
                <p class="card-text"> Centro medico: ${traslado.centroMedico.nombre}</p>
                <p class="card-text">Destino: ${traslado.centroMedico.direccion}</p>
                <a href="finalizarTraslado/${traslado.id}" class="btn btn-success">
                    Finalizar
                </a>
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#traslado${traslado.getId()}">
                        Cancelar
                </button>
                    <!-- The Modal -->
                    <div class="modal fade" id="traslado${traslado.getId()}">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Cancelar Traslado</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body">
                                   <form action="cancelarTraslado/${traslado.getId()}" method="post">
                                       <label for="mensaje">Escriba motivo de cancelacion</label>
                                       <textarea id="mensaje" name="mensaje" rows="4" cols="50" required> mensaje... </textarea>
                                        <input type="submit" class="btn btn-success" value="Confirmar cancelacion">
                                   </form>
                                </div>
                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                </div>
                            </div>
                        </div>
                    </div>

            </div>
        </div>
    </c:forEach>
<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
