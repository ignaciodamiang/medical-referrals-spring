<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%@ page import="org.dom4j.rule.Mode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
            <% String rolMenu = request.getSession().getAttribute("ROL").toString();
            switch (rolMenu){
                case "Solicitador":
            {%>
                <div class="col-2"style="background-color:#008000" id="sticky-sidebar">
                <div class="sticky-top">
                <div class="nav flex-column text-white">
                <p class="m-auto">Bienvenido Solicitador</p>
                <a href="./historialDerivaciones" class="text-decoration-none text-white nav-link">Historial derivacion</a>
            <%}
                    break;
                case "Derivador":
                {%>
                <div class="col-2"style="background-color:#d35400" id="sticky-sidebar">
                <div class="sticky-top">
                <div class="nav flex-column text-white">
                <p class="m-auto">Bienvenido Derivador</p>
            <%}
                    ;break;
                case "Administrativo":
                {%>
            <div class="col-2"style="background-color:#1338BE" id="sticky-sidebar">
            <div class="sticky-top">
            <div class="nav flex-column text-white">
            <p class="m-auto">Bienvenido Administrativo</p>
            <%} ;break;
                default:
                {%>
            <p class="m-auto">Usted no deberia estar aca ¬¬</p>
            <%} ;break;
            }%>

            <a href="./BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
            <a href="./solicitudes-derivaciones" class="text-decoration-none text-white nav-link">Solicitud de derivaciones</a>
            <a href="./listado-derivacion" class="text-decoration-none text-white nav-link">Listado derivaciones</a>
            <a href="#_" class="nav-link">Link</a>
            <a href="#_" class="nav-link">Link</a>
        </div>
    </div>
</div>