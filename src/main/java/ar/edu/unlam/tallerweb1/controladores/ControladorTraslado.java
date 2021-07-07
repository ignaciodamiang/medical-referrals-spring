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
    private ServicioSolicitudDerivacion serviciosolicitudDerivacion;
    private ServicioDerivacion servicioDerivacion;
    private ServicioNotificacion servicioNotificacion;
    private ServicioNotificacionUsuario servicioNotificacionUsuario;
    private ServicioMail servicioMail;

    @Autowired
    public ControladorTraslado(ServicioTraslado servicioTraslado, ServicioCentroMedico servicioCentroMedico, ServicioSolicitudDerivacion solicitudDerivacion, ServicioDerivacion servicioDerivacion, ServicioNotificacion servicioNotificacion,
                               ServicioNotificacionUsuario servicioNotificacionUsuario, ServicioMail servicioMail)
    {this.servicioTraslado = servicioTraslado;
    this.servicioCentroMedico = servicioCentroMedico;
    this.serviciosolicitudDerivacion = solicitudDerivacion;
    this.servicioDerivacion = servicioDerivacion;
    this.servicioNotificacion = servicioNotificacion;
    this.servicioNotificacionUsuario = servicioNotificacionUsuario;
    this.servicioMail = servicioMail;
    }

    @RequestMapping(path = "/crearTraslado/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView crearTraslado(@PathVariable Long idSolicitud){
        SolicitudDerivacion solicitudDerivacion = serviciosolicitudDerivacion.obetenerSolicitudDerivacionPorId(idSolicitud);
        Traslado traslado = new Traslado();
        traslado.setCentroMedico(solicitudDerivacion.getCentroMedico());
        traslado.setDerivacion(solicitudDerivacion.getDerivacion());
        traslado.setEstadoTraslado(EstadoTraslado.ENCURSO);
        solicitudDerivacion.setConfirmado(true);
        Derivacion derivacion = solicitudDerivacion.getDerivacion();
        derivacion.setEstadoDerivacion(EstadoDerivacion.ENTRASLADO);
        Notificacion notificacion = new Notificacion();
        servicioDerivacion.modificarDerivacion(derivacion);
        serviciosolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
        servicioTraslado.guardarTraslado(traslado);
        notificacion.setTraslado(traslado);
        notificacion.setTitulo("Se ha generado un nuevo traslado");
        notificacion.setMensaje("Se ha generado el traslado "+traslado.getId()+ " correspondiente a la derivación "+traslado.getDerivacion().getId() +
                " con destino al centro médico "+traslado.getCentroMedico().getNombre()+ " situado en "+traslado.getCentroMedico().getDireccion());
        servicioNotificacion.guardarNotificacion(notificacion);
        servicioNotificacionUsuario.guardarNotificacionUsuario(notificacion, traslado.getDerivacion().getAutor().getId());
        servicioNotificacionUsuario.guardarNotificacionAdministrativos(traslado.getCentroMedico(), notificacion);
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
        Traslado traslado = servicioTraslado.obtenerTrasladoPorId(idTraslado);
        traslado.setEstadoTraslado(EstadoTraslado.FINALIZADO);
        Derivacion derivacion = traslado.getDerivacion();
        derivacion.setEstadoDerivacion(EstadoDerivacion.FINALIZADA);
        servicioDerivacion.modificarDerivacion(derivacion);
        servicioTraslado.modificarTraslado(traslado);
        servicioMail.enviarMsj(derivacion.getAutor().getEmail(),"Ha finalizado un translado",
                "El traslado del paciente:  '"+derivacion.getPaciente().getNombreCompleto()+
                        "', destino: '"+ traslado.getCentroMedico().getNombre()+" ("+traslado.getCentroMedico().getDireccion()+")"+
                        " ha finalizado correctamente. <br> <a href='http://localhost:8080/proyecto_derivaciones_war_exploded/listado-derivacion'>ver derivaciones</a> <br>");
        return new ModelAndView("redirect:/ver-traslados-encurso");
    }

    @RequestMapping(value = "/cancelarTraslado/{idTraslado}", method = RequestMethod.POST)
    public ModelAndView cancelarTraslado(@PathVariable Long idTraslado, @RequestParam("mensaje") String mensaje) throws MessagingException {
        Traslado traslado = servicioTraslado.obtenerTrasladoPorId(idTraslado);
        traslado.setEstadoTraslado(EstadoTraslado.CANCELADO);
        Notificacion notificacion = new Notificacion();
        notificacion.setTraslado(traslado);
        notificacion.setMensaje(mensaje);
        notificacion.setTitulo("Se ha cancelado el traslado "+ traslado.getId());
        Derivacion derivacion = traslado.getDerivacion();
        derivacion.setEstadoDerivacion(EstadoDerivacion.ENBUSQUEDA);
        servicioNotificacion.guardarNotificacion(notificacion);
        servicioNotificacionUsuario.guardarNotificacionUsuario(notificacion,traslado.getDerivacion().getAutor().getId());
        servicioDerivacion.modificarDerivacion(derivacion);
        servicioTraslado.modificarTraslado(traslado);
        servicioMail.enviarMsj(derivacion.getAutor().getEmail(),"Se ha cancelado un translado",
                "El traslado del paciente:  '"+derivacion.getPaciente().getNombreCompleto()+
                        "', destino: '"+
                        traslado.getCentroMedico().getNombre()+" ("+traslado.getCentroMedico().getDireccion()+")"+
                ", MOTIVO: "+notificacion.getMensaje()+" "+"<br> <a href='http://localhost:8080/proyecto_derivaciones_war_exploded/listado-derivacion'>ver derivaciones</a>");
        return new ModelAndView("redirect:/ver-traslados-encurso");
    }

}
