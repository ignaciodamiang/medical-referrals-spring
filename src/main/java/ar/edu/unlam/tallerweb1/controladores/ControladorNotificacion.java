package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
    public class ControladorNotificacion {
        private ServicioNotificacion servicioNotificacion;
        private ServicioNotificacionUsuario servicioNotificacionUsuario;
@Autowired

    public ControladorNotificacion(ServicioNotificacion servicioNotificacion, ServicioNotificacionUsuario servicioNotificacionUsuario) {
        this.servicioNotificacion = servicioNotificacion;
        this.servicioNotificacionUsuario = servicioNotificacionUsuario;
    }
    @RequestMapping(path = "/notificaciones", method = RequestMethod.GET)
    public ModelAndView verNotificaciones(HttpServletRequest request){
        ModelMap map = new ModelMap();
        map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
        map.put("notificaciones",servicioNotificacionUsuario.obtenerNotificacionPorUsuario(request));
        return new ModelAndView("Notificaciones/notificacionesUsuario", map);
    }
}
