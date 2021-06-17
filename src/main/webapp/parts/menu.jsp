<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%@ page import="org.dom4j.rule.Mode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
            <% String rolMenu = request.getSession().getAttribute("ROL").toString();
            switch (rolMenu){
                case "Solicitador":
            {%>
                <div class="col-2 d-flex flex-column"style="background-color:#008000" id="sticky-sidebar">
                <div class="sticky-top">
                <p class="text-decoration-none text-white nav-link">Bienvenido Solicitador</p>
                <a href="./historialDerivaciones" class="text-decoration-none text-white nav-link">Historial derivacion</a>
                <a href="./BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
            <%}
                    break;
                case "Derivador":
                {%>
                <div class="col-2 d-flex flex-column"style="background-color:#d35400" id="sticky-sidebar">
                <div class="sticky-top">
                <p class="text-decoration-none text-white nav-link">Bienvenido Derivador</p>
                <a href="./BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
                <a href="./listado-derivacion" class="text-decoration-none text-white nav-link">Listado derivaciones</a>
            <%}
                    ;break;
                case "Administrativo":
                {%>
            <div class="col-2 d-flex flex-column"style="background-color:#1338BE" id="sticky-sidebar">
            <div class="sticky-top">
            <p class="text-decoration-none text-white nav-link">Bienvenido Administrativo</p>
                <a href="./BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
                <a href="./solicitudes-derivaciones" class="text-decoration-none text-white nav-link">Solicitud de derivaciones</a>
            <%} ;break;
                default:
                {%>
            <p class="text-decoration-none text-white nav-link">Usted no deberia estar aca ¬¬</p>
            <%} ;break;
            }%>
            <a href="#_" class="text-decoration-none text-white nav-link">otro link</a>
            <a href="./logout" class="text-decoration-none text-white nav-link">Loguout</a>
        </div>
</div>