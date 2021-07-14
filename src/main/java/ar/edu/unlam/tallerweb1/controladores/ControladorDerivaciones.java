package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class ControladorDerivaciones {
	private ServicioDerivacion servicioDerivacion;
	private ServicioPaciente servicioPaciente;
	private ServicioCobertura servicioCobertura;
	private ServicioPlan servicioPlan;
	private ServicioNotificacion servicioNotificacion;
	private ServicioNotificacionUsuario servicioNotificacionUsuario;
	private ServicioRequerimientosMedicos servicioRequerimientosMedicos;
	private ServicioMail servicioMail;
	private ServicioDerivador servicioDerivador;

	@Autowired
	public ControladorDerivaciones(
			ServicioDerivacion servicioDerivacion, ServicioPaciente servicioPaciente,
			ServicioCobertura servicioCobertura, ServicioPlan servicioPlan, ServicioNotificacion servicioNotificacion,
			ServicioNotificacionUsuario servicioNotificacionUsuario, ServicioRequerimientosMedicos servicioRequerimientosMedicos, ServicioMail servicioMail, ServicioDerivador servicioDerivador) {
		this.servicioDerivacion = servicioDerivacion;
		this.servicioPaciente = servicioPaciente;
		this.servicioCobertura = servicioCobertura;
		this.servicioPlan = servicioPlan;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioNotificacionUsuario = servicioNotificacionUsuario;
		this.servicioRequerimientosMedicos = servicioRequerimientosMedicos;
		this.servicioMail = servicioMail;
		this.servicioDerivador = servicioDerivador;
	}

	@RequestMapping(path = "/listado-derivacion")
	public ModelAndView derivaciones(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		if (request.getSession().getAttribute("ROL") == null) {
			return new ModelAndView("redirect:/login");
		}
		if (!request.getSession().getAttribute("ROL").equals("Derivador")) {
			return new ModelAndView("redirect:/router", model);
		}
		Derivacion derivacion = new Derivacion();
		Cobertura cobertura = servicioCobertura
				.obtenerCoberturaPorId((Long) request.getSession().getAttribute("ID_COBERTURA"));
		List<Derivacion> listaDerivaciones = servicioDerivacion.derivacionesPorCobertura(cobertura);
		model.put("derivaciones", listaDerivaciones);
		model.put("eliminarDerivacion", derivacion);
		model.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		return new ModelAndView("Derivaciones/derivaciones", model);
	}

	@RequestMapping(path = "/nueva-derivacion/{id}", method = RequestMethod.GET)
	public ModelAndView nuevaDerivacion(@PathVariable("id") Long idPaciente,HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Paciente paciente = servicioPaciente.obtenerPacientePorId(idPaciente);
		HashSet<Cobertura> coberturas = servicioPlan.obetenerCoberturasPaciente(idPaciente);
		List<String> sectores = new ArrayList<String>();
		sectores.add("guardia");
		sectores.add("salaComun");
		sectores.add("terapia");
		Derivacion derivacion = new Derivacion();
		model.put("sectores", sectores);
		model.put("derivacion", derivacion);
		model.put("paciente", paciente);
		model.put("coberturas", coberturas);
		model.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		return new ModelAndView("Derivaciones/agregar-derivacion", model);
	}

	@RequestMapping(path = "agregar-derivacion", method = RequestMethod.POST)
	public ModelAndView agregarDerivacion(@ModelAttribute("derivacion") Derivacion derivacion,
			RedirectAttributes attributes, @RequestParam("idPaciente") Long idPaciente,
			@RequestParam("urgente") Boolean urgente, @RequestParam(name = "tomografo",defaultValue = "false") Boolean tomografo,
		    @RequestParam(name = "traumatologoGuardia",defaultValue = "false") Boolean traumatologoGuardia,
		    @RequestParam(name = "cirujanoGuardia",defaultValue = "false") Boolean cirujanoGuardia,
		    @RequestParam(name = "cardiologoGuardia",defaultValue = "false") Boolean cardiologoGuardia,
			@RequestParam(name = "ubicacionPaciente") String ubicacionPaciente, HttpServletRequest request) throws MessagingException {

		RequerimientosMedicos requerimientosMedicos = new RequerimientosMedicos();
		requerimientosMedicos.setCirujanoDeGuardia(cirujanoGuardia);
		requerimientosMedicos.setCardiologoSeGuardia(cardiologoGuardia);
		requerimientosMedicos.setTomografo(tomografo);
		requerimientosMedicos.setTraumatologoDeguardia(traumatologoGuardia);
		servicioDerivacion.guardarDerivacion(derivacion, request, idPaciente, requerimientosMedicos, urgente, ubicacionPaciente);
		attributes.addFlashAttribute("message", "Se creo la derivación correctamente");
		/*  se mande un mail a todos los derivadores de esa cobertura cuando se genera una derivacion  */
		/* testear correctamente con correos reales */
		Cobertura cobertura= derivacion.getCobertura();
		for (Derivador derivador : servicioDerivador.obtenerDerivadoresPorCobertura(cobertura)){
			servicioMail.enviarMsj(derivador.getUsuario().getEmail(),"Se ha generado una derivacion.","se ha generado una derivacion para paciente: "+derivacion.getPaciente().getNombreCompleto());
		}
		return new ModelAndView("redirect:/BuscarPaciente");
	}

	@RequestMapping(path = "/modificar-derivacion/editar", method = RequestMethod.GET)
	public ModelAndView modificarDerivacion(@RequestParam("id") Long id ,HttpServletRequest request) throws Exception {
		ModelMap model = new ModelMap();
		Derivacion derivacion = servicioDerivacion.verDerivacion(id);
//        Paciente paciente = servicioPaciente.obtenerPacientePorDocumento(id);
//        List<Cobertura> coberturas = servicioCobertura.obtenerCoberturas();
		model.put("derivacion", derivacion);
//        model.put("pacientes",paciente);
//        model.put("coberturas",coberturas);
		model.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		return new ModelAndView("Derivaciones/modificar-derivacion", model);
	}

	@RequestMapping(path = "/modificar-derivacion/editar", method = RequestMethod.POST)
	public ModelAndView modificarDatosPost(@ModelAttribute("derivacion") Derivacion derivacion,
			RedirectAttributes attributes) {
		derivacion.setFechaDerivacion(new Date());
		servicioDerivacion.modificarDerivacion(derivacion);
		attributes.addFlashAttribute("message", "Se modifico la derivacion exitosamente.");
		return new ModelAndView("redirect:/listado-derivacion");
	}

	@RequestMapping(path = "cancelar-derivacion/{id}", method = RequestMethod.POST)
	public ModelAndView eliminarDerivacion(@PathVariable("id") Long id, @RequestParam("mensaje") String mensaje,
			HttpServletRequest request) throws Exception {
		Derivacion derivacion = servicioDerivacion.verDerivacion(id);
		derivacion.setEstadoDerivacion(EstadoDerivacion.CANCELADA);
		servicioDerivacion.modificarDerivacion(derivacion);
		Notificacion notificacion = new Notificacion();
		notificacion.setDerivacion(derivacion);
		notificacion.setMensaje(mensaje);
		notificacion.setTitulo("Se ha cancelado la derivación" + derivacion.getId());
		servicioNotificacion.guardarNotificacion(notificacion);
		servicioNotificacionUsuario.guardarNotificacionDerivadores(derivacion.getCobertura(), notificacion);
		return new ModelAndView("redirect:/router");
	}

	@RequestMapping(path = "historialDerivaciones", method = RequestMethod.GET)
	public ModelAndView historialDerivaciones(HttpServletRequest request) throws ParseException {
		ModelMap map = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("ID_USUARIO");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaMax = sdf.format(new Date());
		List<Derivacion> derivaciones = servicioDerivacion.filtrarDerivacionesPorFecha(idUsuario, "1900-01-01",
				fechaMax);
		map.put("derivaciones", derivaciones);
		map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		return new ModelAndView("Derivaciones/historial-derivaciones", map);
	}

	@RequestMapping(path = "filtrarDerivaciones", method = RequestMethod.POST)
	public ModelAndView filtrarDerivaciones(@RequestParam String fechaMin, @RequestParam String fechaMax,
			HttpServletRequest request) throws ParseException {
		ModelMap map = new ModelMap();
		if (fechaMin.equals("") && fechaMax.equals("")) {
			return new ModelAndView("redirect:/historialDerivaciones");
		}
		if (fechaMin.equals("")) {
			fechaMin = "1900-01-01";
		}
		if (fechaMax.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fechaMax = sdf.format(new Date());
		}
		Long idUsuario = (Long) request.getSession().getAttribute("ID_USUARIO");
		List<Derivacion> derivaciones = servicioDerivacion.filtrarDerivacionesPorFecha(idUsuario, fechaMin, fechaMax);
		map.put("derivaciones", derivaciones);
		map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		return new ModelAndView("Derivaciones/historial-derivaciones", map);
	}
}
