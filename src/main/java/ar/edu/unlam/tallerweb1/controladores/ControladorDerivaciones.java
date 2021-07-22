package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private ServicioNotificacionUsuario servicioNotificacionUsuario;
	private ServicioCentroMedico servicioCentroMedico;
	private ServicioSolicitudDerivacion servicioSolicitudDerivacion;
	private ServicioComentario servicioComentario;
	private ServicioAdjunto servicioAdjunto;

	@Autowired
	public ControladorDerivaciones(
			ServicioDerivacion servicioDerivacion, ServicioPaciente servicioPaciente,
			ServicioCobertura servicioCobertura, ServicioPlan servicioPlan,
			ServicioNotificacionUsuario servicioNotificacionUsuario, ServicioCentroMedico servicioCentroMedico,
			ServicioSolicitudDerivacion servicioSolicitudDerivacion, ServicioComentario servicioComentario,
			ServicioAdjunto servicioAdjunto) {
		this.servicioDerivacion = servicioDerivacion;
		this.servicioPaciente = servicioPaciente;
		this.servicioCobertura = servicioCobertura;
		this.servicioPlan = servicioPlan;
		this.servicioNotificacionUsuario = servicioNotificacionUsuario;
		this.servicioCentroMedico = servicioCentroMedico;
		this.servicioSolicitudDerivacion = servicioSolicitudDerivacion;
		this.servicioComentario = servicioComentario;
		this.servicioAdjunto = servicioAdjunto;
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
	@RequestMapping(path = "/buscar-derivacion",method = RequestMethod.GET)
	public  ModelAndView buscarDerivacionPorCodigo(@RequestParam("codigoDerivacion") String codigo ,RedirectAttributes attributes){
		Derivacion derivacion = servicioDerivacion.obtenerDerivacionPorCodigo(codigo);
		if(derivacion != null){
			attributes.addFlashAttribute("message", "Derivacion encontrada !");
			return new ModelAndView("redirect:/ver-derivacion?id="+derivacion.getId());
		}
		attributes.addFlashAttribute("message", "No se encontro la derivacion ! ");
		return new ModelAndView("redirect:/BuscarPaciente");

	}

	@RequestMapping(path = "/ver-derivacion",method = RequestMethod.GET)
	public ModelAndView verDerivacion(@RequestParam("id") Long idDerivacion, HttpServletRequest request) throws Exception {
		ModelMap model = new ModelMap();
		List<SolicitudDerivacion> lista= servicioSolicitudDerivacion.obtenerSolicitudesDeDerivacionPorDerivacion(idDerivacion);
		Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
		List<Comentario> comentarios = servicioComentario.obtenerComentariosPorDerivacion(derivacion);
		List<Adjunto> adjuntos = servicioAdjunto.listarAdjuntosPorDerivacion(derivacion);
		model.put("rol",request.getSession().getAttribute("ROL"));
		model.put("listaSolicitudesDerivaciones",lista);
		model.put("derivacion",derivacion);
		model.put("comentarios", comentarios);
		model.put("adjuntos", adjuntos);
		model.put("path", request.getSession().getServletContext().getRealPath("/img/adjuntos/"));
		model.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
	return new ModelAndView("Derivaciones/ver-derivacion",model);
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
		model.put("rol", request.getSession().getAttribute("ROL"));
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
			@RequestParam(name = "ubicacionPaciente") String ubicacionPaciente, HttpServletRequest request) throws Exception {
		RequerimientosMedicos requerimientosMedicos = new RequerimientosMedicos();
		requerimientosMedicos.setCirujanoDeGuardia(cirujanoGuardia);
		requerimientosMedicos.setCardiologoSeGuardia(cardiologoGuardia);
		requerimientosMedicos.setTomografo(tomografo);
		requerimientosMedicos.setTraumatologoDeguardia(traumatologoGuardia);
		servicioDerivacion.guardarDerivacion(derivacion, derivacion.getCobertura().getId(), request, idPaciente, requerimientosMedicos, urgente, ubicacionPaciente);
		attributes.addFlashAttribute("message", "Se creo la derivación correctamente");
		return new ModelAndView("redirect:/ver-derivacion?id="+derivacion.getId());
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
		servicioDerivacion.cancelarDerivacion(id,mensaje,request);
		return new ModelAndView("redirect:/router");
	}

	@RequestMapping(path = "historialDerivaciones", method = RequestMethod.GET)
	public ModelAndView historialDerivaciones(HttpServletRequest request) throws Exception {
		ModelMap map = new ModelMap();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaMax = sdf.format(new Date());

		if (request.getSession().getAttribute("ID_SOLICITADOR")!=null){
			Long idUsuario = (Long) request.getSession().getAttribute("ID_USUARIO");
			List<Derivacion> derivaciones = servicioDerivacion.filtrarDerivacionesFinalizadasYCanceladasPorFechaYUsuario(idUsuario, "1900-01-01",
					fechaMax);
			map.put("derivaciones", derivaciones);
		}

		if (request.getSession().getAttribute("ID_DERIVADOR")!=null){
			List<Derivacion> derivaciones = servicioDerivacion.derivacionesPorCoberturaYFechaFinalizadasYCanceladas(request, "1900-01-01",
					fechaMax);
			map.put("derivaciones", derivaciones);
			map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		}

		if (request.getSession().getAttribute("ID_ADMINISTRATIVO")!=null){
			List<Derivacion> derivaciones = servicioDerivacion.derivacionesPorCentroMedicoYFechaFinalizadasYCanceladas(request, "1900-01-01", fechaMax);
			map.put("derivaciones", derivaciones);
			map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		}

		map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		return new ModelAndView("Derivaciones/historial-derivaciones", map);
	}

	@RequestMapping(path = "filtrarDerivaciones", method = RequestMethod.POST)
	public ModelAndView filtrarDerivaciones(@RequestParam String fechaMin, @RequestParam String fechaMax,
			HttpServletRequest request) throws Exception {
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

		if (request.getSession().getAttribute("ID_SOLICITADOR")!=null) {
			Long idUsuario = (Long) request.getSession().getAttribute("ID_USUARIO");
			List<Derivacion> derivaciones = servicioDerivacion.filtrarDerivacionesFinalizadasYCanceladasPorFechaYUsuario(idUsuario, fechaMin, fechaMax);
			map.put("derivaciones", derivaciones);
			map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		}

		if (request.getSession().getAttribute("ID_DERIVADOR")!=null) {
			List<Derivacion> derivaciones = servicioDerivacion.derivacionesPorCoberturaYFechaFinalizadasYCanceladas(request, fechaMin, fechaMax);
			map.put("derivaciones", derivaciones);
			map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		}

		if (request.getSession().getAttribute("ID_ADMINISTRATIVO")!=null) {
			List<Derivacion> derivaciones = servicioDerivacion.derivacionesPorCentroMedicoYFechaFinalizadasYCanceladas(request, fechaMin, fechaMax);
			map.put("derivaciones", derivaciones);
			map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
		}

		return new ModelAndView("Derivaciones/historial-derivaciones", map);
	}

	@RequestMapping(path = "/nueva-derivacion-centro-medico/{id}", method = RequestMethod.GET)
	public ModelAndView nuevaDerivacionDeCentroMedico(@PathVariable("id") Long idPaciente,HttpServletRequest request) throws Exception {
		if (!request.getSession().getAttribute("ROL").equals("Administrativo")) {
			return new ModelAndView("redirect:/router");
		}
		ModelMap model = new ModelMap();
		String direccionCentroMedico = servicioCentroMedico.obtenerCentroMedicoPorId((Long)request.getSession().getAttribute("ID_CENTROMEDICO")).getDireccion();
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
		model.put("direccionCentroMedico", direccionCentroMedico);
		model.put("rol", request.getSession().getAttribute("ROL"));
		model.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));

		return new ModelAndView("Derivaciones/agregar-derivacion", model);
	}

	@RequestMapping(path = "agregar-derivacion-centro-medico", method = RequestMethod.POST)
	public ModelAndView agregarDerivacionCentroMedico(@ModelAttribute("derivacion") Derivacion derivacion,
										  RedirectAttributes attributes, @RequestParam("idPaciente") Long idPaciente,
										  @RequestParam("urgente") Boolean urgente, @RequestParam(name = "tomografo",defaultValue = "false") Boolean tomografo,
										  @RequestParam(name = "traumatologoGuardia",defaultValue = "false") Boolean traumatologoGuardia,
										  @RequestParam(name = "cirujanoGuardia",defaultValue = "false") Boolean cirujanoGuardia,
										  @RequestParam(name = "cardiologoGuardia",defaultValue = "false") Boolean cardiologoGuardia,
										  @RequestParam(name = "ubicacionPaciente") String ubicacionPaciente, HttpServletRequest request) throws Exception {

		RequerimientosMedicos requerimientosMedicos = new RequerimientosMedicos();
		requerimientosMedicos.setCirujanoDeGuardia(cirujanoGuardia);
		requerimientosMedicos.setCardiologoSeGuardia(cardiologoGuardia);
		requerimientosMedicos.setTomografo(tomografo);
		requerimientosMedicos.setTraumatologoDeguardia(traumatologoGuardia);
		servicioDerivacion.guardarDerivacionCentroMedico(derivacion, request, idPaciente, requerimientosMedicos, urgente, ubicacionPaciente);
		attributes.addFlashAttribute("message", "Se creo la derivación correctamente");
		return new ModelAndView("redirect:/BuscarPaciente");
	}

	@RequestMapping(path = "adjuntar-archivo-derivacion/{idDerivacion}", method = RequestMethod.POST)
	public ModelAndView adjuntarArchivo(@RequestParam("adjunto") MultipartFile adjunto, @PathVariable Long idDerivacion,
										HttpServletRequest request, @RequestParam("titulo") String titulo) throws Exception {
		Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
		if(derivacion != null){
			String path = request.getSession().getServletContext().getRealPath("/img/adjuntos/");
			servicioAdjunto.guardarImagen(adjunto,path,derivacion,titulo);
		}
		return new ModelAndView("redirect:/ver-derivacion?id="+derivacion.getId());
	}
}
