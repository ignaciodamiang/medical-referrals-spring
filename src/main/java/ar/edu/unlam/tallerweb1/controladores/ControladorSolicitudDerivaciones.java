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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorSolicitudDerivaciones {
    private ServicioSolicitudDerivacion servicioSolicitudDerivacion;
    private ServicioDerivacion servicioDerivacion;
    private ServicioCentroMedico servicioCentroMedico;
    private ServicioNotificacion servicioNotificacion;
    private ServicioNotificacionUsuario servicioNotificacionUsuario;

    @Autowired
    public ControladorSolicitudDerivaciones
            (ServicioSolicitudDerivacion servicio, ServicioDerivacion servicioDerivacion, ServicioCentroMedico servicioCentroMedico,
             ServicioNotificacion servicioNotificacion, ServicioNotificacionUsuario servicioNotificacionUsuario) {
        this.servicioSolicitudDerivacion = servicio;
        this.servicioDerivacion = servicioDerivacion;
        this.servicioCentroMedico = servicioCentroMedico;
        this.servicioNotificacion = servicioNotificacion;
        this.servicioNotificacionUsuario = servicioNotificacionUsuario;
    }

    @RequestMapping("/solicitudes-derivaciones")
    public ModelAndView mostrarSolicitudesDerivaciones( HttpServletRequest request) throws Exception {
        ModelMap modelo = new ModelMap();
        Long id = (Long) request.getSession().getAttribute("ID_CENTROMEDICO");
        CentroMedico centro = servicioCentroMedico.obtenerCentroMedicoPorId(id);
        List<SolicitudDerivacion> lista = servicioSolicitudDerivacion.obtenerSolicitudesDeDerivacionPorCentroMedico(centro);
        modelo.put("listaSolicitudesDerivaciones", lista);
        modelo.put("rol",request.getSession().getAttribute("ROL"));
        return new ModelAndView("/solicitud-derivaciones/solicitud-derivaciones", modelo);
    }

    @RequestMapping(path = "/nueva-solicitud-derivacion/{idDerivacion}",method = RequestMethod.GET)
    public ModelAndView nuevaSolicitudDerivacion(@PathVariable Long idDerivacion) throws Exception {
        ModelMap model = new ModelMap();
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
        model.put("derivaciones", derivacion);
        model.put("centrosMedicos", servicioCentroMedico.obtenerCentrosMedicosPorPaciente(derivacion.getPaciente()));
        //model.put("centrosMedicos", servicioCentroMedico.obtenerCentrosMedicos());
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
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo("Se ha generado una nueva solicitud de derivación");
        notificacion.setMensaje("Se ha generado una nueva solicitud de derivación para el centro medico " +solicitudDerivacion.getCentroMedico()
                                +" perteneciente al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto());
        notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
        servicioNotificacion.guardarNotificacion(notificacion);
        servicioNotificacionUsuario.guardarNotificacionAdministrativos(solicitudDerivacion.getCentroMedico(), notificacion);
        attributes.addFlashAttribute("message","Se creo la solicitud derivación correctamente");
        return new ModelAndView("redirect:/listado-derivacion");
    }

    @RequestMapping(path = "aceptarSolicitud/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView aceptarSolicitud(@PathVariable Long idSolicitud){
    SolicitudDerivacion solicitudDerivacion = servicioSolicitudDerivacion.obetenerSolicitudDerivacionPorId(idSolicitud);
    solicitudDerivacion.setAceptado(true);
    solicitudDerivacion.setId(idSolicitud);
    servicioSolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
    Notificacion notificacion = new Notificacion();
    notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
    notificacion.setTitulo("Se ha aceptado la solicitud de Derivación numero "+solicitudDerivacion.getId());
    notificacion.setMensaje("La solicitud realizada al centro médico "+solicitudDerivacion.getCentroMedico().getNombre()
            +" para derivar al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto()
            + " ha sido aceptada ya puede generar el traslado correspondiente");
    servicioNotificacion.guardarNotificacion(notificacion);
    servicioNotificacionUsuario.guardarNotificacionDerivadores(solicitudDerivacion.getDerivacion().getCobertura(), notificacion);
    return new ModelAndView("redirect:/solicitudes-derivaciones");
    }

    @RequestMapping(path = "rechazarSolicitud/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView rechazarSolicitud(@PathVariable Long idSolicitud){
        SolicitudDerivacion solicitudDerivacion = servicioSolicitudDerivacion.obetenerSolicitudDerivacionPorId(idSolicitud);
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setId(idSolicitud);
        servicioSolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
        Notificacion notificacion = new Notificacion();
        notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
        notificacion.setTitulo("Se ha rechazado la solicitud de Derivación numero "+solicitudDerivacion.getId());
        notificacion.setMensaje("La solicitud realizada al centro médico "+solicitudDerivacion.getCentroMedico().getNombre()
                +" para derivar al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto()
                + " ha sido rechazada por favor buscar otro centro médico");
        servicioNotificacion.guardarNotificacion(notificacion);
        servicioNotificacionUsuario.guardarNotificacionDerivadores(solicitudDerivacion.getDerivacion().getCobertura(), notificacion);
        return new ModelAndView("redirect:/solicitudes-derivaciones");
    }
    @RequestMapping(path = "verSolicitudes/{idDerivacion}",method = RequestMethod.GET)
    public ModelAndView verSolicitudDerivacionPorDerivacion(@PathVariable Long idDerivacion,HttpServletRequest request){
        ModelMap model = new ModelMap();
        List<SolicitudDerivacion> lista= servicioSolicitudDerivacion.obtenerSolicitudesDeDerivacionPorDerivacion(idDerivacion);
        model.put("rol",request.getSession().getAttribute("ROL"));
        model.put("listaSolicitudesDerivaciones",lista);
        return new ModelAndView("/solicitud-derivaciones/solicitud-derivaciones", model);

    }

}