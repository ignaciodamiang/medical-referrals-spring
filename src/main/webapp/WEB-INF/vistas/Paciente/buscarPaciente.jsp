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
        <div class="col-12" id="main">
            <!--  fin menu -->
            <div class="col-2">
                <form action="ObtenerPaciente" method="post">
                    <input class="form-control mb-1" placeholder="Documento del paciente" name="documento" type="search" id="documento"/>
                    <button type="submit" class="btn btn-primary w-100">Buscar</button>
                </form>
            </div>
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
                    <c:choose>
                        <c:when test="${!rol.equals('Administrativo')}">
                            <a class="btn btn-primary" href="nueva-derivacion/${paciente.id}" role="button">Generar nueva derivación</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary" href="nueva-derivacion-centro-medico/${paciente.id}" role="button">Generar nueva derivación</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>

                <div class="d-flex flex-wrap mb-3">

                <c:if test="${not empty derivaciones}">
                <h3 class="text-center">Derivaciones activas</h3>
                <table class="table table-hover table-striped table-bordered border-primary">
                <thead>
                <tr>
                    <th scope="col">Código</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Nombre completo</th>
                    <th scope="col">Documento</th>
                    <th scope="col">Diagnóstico</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Complejidad</th>
                    <th scope="col">Cobertura</th>
                </tr>
                </thead>
                <tbody>

                </c:if>
                    <c:forEach items="${derivaciones}" var="derivacion">
                        <tr>
                            <th scope="row"><a href="ver-derivacion?id=${derivacion.getId()}">${derivacion.getCodigo()}</a></th>
                            <td>${derivacion.getFechaDerivacion()}</td>
                            <td>${derivacion.getPaciente().getNombreCompleto()}</td>
                            <td>${derivacion.getPaciente().getDocumento()}</td>
                            <td>${derivacion.getDiagnostico()}</td>
                            <c:if test="${derivacion.getEstadoDerivacion().toString().equals('ENTRASLADO')}">
                                <td>En traslado</td>
                            </c:if>
                            <c:if test="${derivacion.getEstadoDerivacion().toString().equals('ENBUSQUEDA')}">
                                <td>En búsqueda</td>
                            </c:if>
                            <td>${derivacion.getParaQueSector()}</td>
                            <td>${derivacion.getCobertura().getNombre()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!--  footer -->
        </div>

<%@ include file="../../../parts/footer.jsp" %>
</body>
</html>
