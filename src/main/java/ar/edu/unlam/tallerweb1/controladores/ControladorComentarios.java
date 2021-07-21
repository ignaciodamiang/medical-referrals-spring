package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComentario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorComentarios {
    private ServicioComentario servicioComentario;
    private ServicioSolicitudDerivacion servicioSolicitudDerivacion;
    private ServicioDerivacion servicioDerivacion;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorComentarios(ServicioComentario servicioComentario,ServicioSolicitudDerivacion servicioSolicitudDerivacion,
                                  ServicioUsuario servicioUsuario, ServicioDerivacion servicioDerivacion){
       this.servicioComentario=servicioComentario;
       this.servicioSolicitudDerivacion=servicioSolicitudDerivacion;
       this.servicioUsuario = servicioUsuario;
       this.servicioDerivacion = servicioDerivacion;
    }

    @RequestMapping(path = "/agregarComentarioSolicitudDerivacion/{idSolicitud}", method = RequestMethod.POST)
    public ModelAndView agregarComentarioSolicitudDerivacion(@RequestParam("mensaje") String mensaje,
                                                             @PathVariable Long idSolicitud, HttpServletRequest request) {
        Usuario usuario = servicioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        SolicitudDerivacion solicitudDerivacion = servicioSolicitudDerivacion.obtenerSolicitudDerivacionPorId(idSolicitud);
        if(usuario != null && solicitudDerivacion != null) {
            servicioComentario.guardarComentarioSolicitudDerivacion(solicitudDerivacion, mensaje, usuario, "C");
        }
        return new ModelAndView("redirect:/ver-solicitud-derivacion/"+idSolicitud);
    }

    @RequestMapping(path = "/agregarComentarioDerivacion/{idDerivacion}", method = RequestMethod.POST)
    public ModelAndView agregarComentarioDerivacion(@RequestParam("mensaje") String mensaje,
                                                             @PathVariable Long idDerivacion, HttpServletRequest request) throws Exception {
        Usuario usuario = servicioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
        if(usuario != null && derivacion != null) {
            servicioComentario.guardarComentarioDerivacion(derivacion, mensaje, usuario, "R");
        }
        return new ModelAndView("redirect:/ver-derivacion?id="+idDerivacion);
    }
}
