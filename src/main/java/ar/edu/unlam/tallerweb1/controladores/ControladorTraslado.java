package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class ControladorTraslado {

    private ServicioTraslado servicioTraslado;
    private ServicioCentroMedico servicioCentroMedico;
    private ServicioNotificacionUsuario servicioNotificacionUsuario;

    @Autowired
    public ControladorTraslado(ServicioTraslado servicioTraslado, ServicioCentroMedico servicioCentroMedico,
                               ServicioNotificacionUsuario servicioNotificacionUsuario)
    {this.servicioTraslado = servicioTraslado;
    this.servicioCentroMedico = servicioCentroMedico;
    this.servicioNotificacionUsuario = servicioNotificacionUsuario;
    }

    @RequestMapping(path = "/crearTraslado/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView crearTraslado(@PathVariable Long idSolicitud){
        servicioTraslado.guardarTraslado(idSolicitud);
        return new ModelAndView("redirect:/listado-derivacion");
    }

    @RequestMapping(path = "/traslados", method = RequestMethod.GET)
    public ModelAndView verTraslados(HttpServletRequest request){
        List<Traslado> traslados = servicioTraslado.obtenerTraslados();
        ModelMap map = new ModelMap();
        map.put("traslados", traslados);
        map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
        return new ModelAndView("Traslado/traslados", map);
    }

    @RequestMapping(path = "/ver-traslado/{idDerivacion}", method = RequestMethod.GET)
    public ModelAndView verTraslados(@PathVariable Long idDerivacion, HttpServletRequest request) throws InterruptedException, IOException, URISyntaxException {
        Traslado traslado = servicioTraslado.obtenerTrasladoPorDerivacion(idDerivacion);
        ModelMap map = new ModelMap();
        Maps coordenadas = new Maps();
        map.put("traslado", traslado);
        map.put("coordenadas",coordenadas.obtenerCoordenadas(traslado.getCentroMedico().getDireccion()));
        map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
        return new ModelAndView("Traslado/ver-traslado", map);
    }

    @RequestMapping(path = "/ver-traslados-encurso", method = RequestMethod.GET)
    public ModelAndView verTrasladosEnCurso(HttpServletRequest request) throws Exception {
        ModelMap map = new ModelMap();
        CentroMedico centroMedico = servicioCentroMedico.obtenerCentroMedicoPorId(
                (Long) request.getSession().getAttribute("ID_CENTROMEDICO"));
        List<Traslado> traslados = servicioTraslado.obtenerTrasladosPorCentroMedico(centroMedico);
        map.put("traslados", traslados);
        map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
        return new ModelAndView("Traslado/traslados-enCurso", map);

    }

    @RequestMapping(value = "/finalizarTraslado/{idTraslado}", method = RequestMethod.GET)
    public ModelAndView finalizarTraslado(@PathVariable Long idTraslado) throws MessagingException {
        servicioTraslado.finalizarTraslado(idTraslado);
        return new ModelAndView("redirect:/ver-traslados-encurso");
    }

    @RequestMapping(value = "/cancelarTraslado/{idTraslado}", method = RequestMethod.POST)
    public ModelAndView cancelarTraslado(@PathVariable Long idTraslado, @RequestParam("mensaje") String mensaje) throws MessagingException {
        servicioTraslado.cancelarTraslado(idTraslado, mensaje);
        return new ModelAndView("redirect:/ver-traslados-encurso");
    }

}
