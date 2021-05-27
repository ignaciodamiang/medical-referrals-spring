package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudDerivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class ControladorSolicitudDerivaciones {
    private ServicioSolicitudDerivacion servicioSolicitudDerivacion;
    private ServicioDerivacion servicioDerivacion;
    private ServicioCentroMedico servicioCentroMedico;

    @Autowired
    public ControladorSolicitudDerivaciones
            (ServicioSolicitudDerivacion servicio, ServicioDerivacion servicioDerivacion, ServicioCentroMedico servicioCentroMedico) {
        this.servicioSolicitudDerivacion = servicio;
        this.servicioDerivacion = servicioDerivacion;
        this.servicioCentroMedico = servicioCentroMedico;
    }

    @RequestMapping("/solicitudes-derivaciones")
    public ModelAndView mostrarSolicitudesDerivaciones() {
        ModelMap modelo = new ModelMap();
        List<SolicitudDerivacion> lista = servicioSolicitudDerivacion.obtenerSolicitudesDeDerivacion();
        modelo.put("listaSolicitudesDerivaciones", lista);
        return new ModelAndView("/solicitud-derivaciones/solicitud-derivaciones", modelo);
    }

    @RequestMapping(path = "/nueva-solicitud-derivacion/{idDerivacion}",method = RequestMethod.GET)
    public ModelAndView nuevaSolicitudDerivacion(@PathVariable Long idDerivacion) throws Exception {
        ModelMap model = new ModelMap();
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
        model.put("derivaciones", derivacion);
        model.put("centrosMedicos", servicioCentroMedico.obtenerCentrosMedicos());
        model.put("solicitudDerivacion", solicitudDerivacion);
        return new ModelAndView("/solicitud-derivaciones/agregar-solicitud-derivacion", model);
    }

    @RequestMapping(path="agregar-solicitud-derivacion", method = RequestMethod.POST)
    public ModelAndView agregarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion, RedirectAttributes attributes,
                                                   @RequestParam("idDerivacion") Long idDerivacion) throws Exception {

        solicitudDerivacion.setFechaCreacion(new Date());
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setConfirmado(false);
        Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
        solicitudDerivacion.setDerivacion(derivacion);
        servicioSolicitudDerivacion.guardarSolicitudDerivacion(solicitudDerivacion);
        attributes.addFlashAttribute("message","Se creo la solicitud derivación correctamente");
        return new ModelAndView("redirect:solicitudes-derivaciones");
    }

    @RequestMapping(path = "aceptarSolicitud/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView aceptarSolicitud(@PathVariable Long idSolicitud){
    SolicitudDerivacion solicitudDerivacion = servicioSolicitudDerivacion.obetenerSolicitudDerivacionPorId(idSolicitud);
    solicitudDerivacion.setAceptado(true);
    solicitudDerivacion.setId(idSolicitud);
    servicioSolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
    return new ModelAndView("redirect:/solicitudes-derivaciones");
    }

    @RequestMapping(path = "rechazarSolicitud/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView rechazarSolicitud(@PathVariable Long idSolicitud){
        SolicitudDerivacion solicitudDerivacion = servicioSolicitudDerivacion.obetenerSolicitudDerivacionPorId(idSolicitud);
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setId(idSolicitud);
        servicioSolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
        return new ModelAndView("redirect:/solicitudes-derivaciones");
    }

}