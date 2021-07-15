<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%@ page import="org.dom4j.rule.Mode" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
            <% String rolMenu = request.getSession().getAttribute("ROL").toString();
            switch (rolMenu){
                case "Solicitador":
            {%>
                <div class="col-2 d-flex flex-column solicitador" id="sticky-sidebar">
                <div class="sticky-top">
                <p class="text-decoration-none text-white nav-link">Bienvenido Solicitador</p>
                <a href="/proyecto_derivaciones_war_exploded/historialDerivaciones" class="text-decoration-none text-white nav-link">Historial derivacion</a>
                <a href="/proyecto_derivaciones_war_exploded/BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
            <%}
                    break;
                case "Derivador":
                {%>
                <div class="col-2 d-flex flex-column derivador" id="sticky-sidebar">
                <div class="sticky-top">
                <p class="text-decoration-none text-white nav-link">Bienvenido Derivador</p>
                <a href="/proyecto_derivaciones_war_exploded/BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
                <a href="/proyecto_derivaciones_war_exploded/listado-derivacion" class="text-decoration-none text-white nav-link">Listado derivaciones</a>
                <a href="/proyecto_derivaciones_war_exploded/historial-derivaciones-derivador" class="text-decoration-none text-white nav-link">Historial derivaciones</a>
            <%}
                    ;break;
                case "Administrativo":
                {%>
            <div class="col-2 d-flex flex-column administrativo" id="sticky-sidebar">
            <div class="sticky-top">
            <p class="text-decoration-none text-white nav-link">Bienvenido Administrativo</p>
                <a href="/proyecto_derivaciones_war_exploded/BuscarPaciente" class="text-decoration-none text-white nav-link">Buscar paciente</a>
                <a href="/proyecto_derivaciones_war_exploded/solicitudes-derivaciones" class="text-decoration-none text-white nav-link">Solicitud de derivaciones</a>
                <a href="/proyecto_derivaciones_war_exploded/RequerimientosMedicos" class="text-decoration-none text-white nav-link">Requerimientos Médicos</a>
            <%} ;break;
                default:
                {%>
            <p class="text-decoration-none text-white nav-link">Usted no deberia estar aca ¬¬</p>
            <%} ;break;
            }%>
            <a href="/proyecto_derivaciones_war_exploded/notificaciones" class="text-decoration-none text-white nav-link"> Notificaciones (${cantNotificacion})</a>
            <a href="/proyecto_derivaciones_war_exploded/logout" class="text-decoration-none text-white nav-link">Loguout</a>
        </div>
</div>