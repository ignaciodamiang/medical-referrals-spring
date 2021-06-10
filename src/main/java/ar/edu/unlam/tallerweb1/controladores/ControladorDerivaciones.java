package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.EstadoDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobertura;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.zip.DeflaterInputStream;

@Controller
public class ControladorDerivaciones {
    private ServicioDerivacion servicioDerivacion;
    private ServicioPaciente servicioPaciente;
    private ServicioCobertura servicioCobertura;
    private ServicioPlan servicioPlan;

    @Autowired
    public ControladorDerivaciones(ServicioDerivacion servicioDerivacion, ServicioPaciente servicioPaciente, ServicioCobertura servicioCobertura, ServicioPlan servicioPlan){
        this.servicioDerivacion = servicioDerivacion;
        this.servicioPaciente = servicioPaciente;
        this.servicioCobertura = servicioCobertura;
        this.servicioPlan = servicioPlan;
    }

    @RequestMapping(path = "/listado-derivacion")
    public ModelAndView derivaciones(HttpServletRequest request){
        ModelMap model = new ModelMap();
        if(request.getSession().getAttribute("ROL") == null){
            return new ModelAndView("redirect:/login");
        }
        Derivacion derivacion = new Derivacion();
        Cobertura cobertura = servicioCobertura.obtenerCoberturaPorId((Long)request.getSession().getAttribute("ID_COBERTURA"));
        List<Derivacion> listaDerivaciones = servicioDerivacion.derivacionesPorCobertura(cobertura);
        model.put("derivaciones", listaDerivaciones);
        model.put("eliminarDerivacion", derivacion);
        return new ModelAndView("Derivaciones/derivaciones",model);
    }

    @RequestMapping(path = "/nueva-derivacion/{id}", method = RequestMethod.GET)
    public ModelAndView nuevaDerivacion(@PathVariable("id") Long idPaciente){
        ModelMap model = new ModelMap();
        Paciente paciente = servicioPaciente.obtenerPacientePorId(idPaciente);
        HashSet<Cobertura> coberturas = servicioPlan.obetenerCoberturasPaciente(idPaciente);
        List<String> sectores = new ArrayList<String>();
        sectores.add("guardia");sectores.add("salaComun");sectores.add("terapia");
        Derivacion derivacion = new Derivacion();
        model.put("sectores", sectores);
        model.put("derivacion", derivacion);
        model.put("paciente",paciente);
        model.put("coberturas",coberturas);

        return new ModelAndView("Derivaciones/agregar-derivacion",model);
    }


    @RequestMapping(path="agregar-derivacion", method = RequestMethod.POST)
    public ModelAndView agregarDerivacion(@ModelAttribute("derivacion") Derivacion derivacion
                                          ,RedirectAttributes attributes
                                          ,@RequestParam("idPaciente") Long idPaciente
                                          ,@RequestParam("urgente") Boolean urgente, HttpServletRequest request){

        Paciente paciente = servicioPaciente.obtenerPacientePorId(idPaciente);
        derivacion.setPaciente(paciente);
        derivacion.setUrgente(urgente);
        derivacion.setFechaDerivacion(new Date());
        derivacion.setEstadoDerivacion(EstadoDerivacion.ENBUSQUEDA);

        servicioDerivacion.guardarDerivacion(derivacion, request);
        attributes.addFlashAttribute("message","Se creo la derivación correctamente");
        return new ModelAndView("redirect:/BuscarPaciente");
    }


    @RequestMapping(path = "/modificar-derivacion/editar" , method = RequestMethod.GET)
    public ModelAndView modificarDerivacion(@RequestParam("id") Long id) throws Exception {
        ModelMap model = new ModelMap();
        Derivacion derivacion = servicioDerivacion.verDerivacion(id);
//        Paciente paciente = servicioPaciente.obtenerPacientePorDocumento(id);
//        List<Cobertura> coberturas = servicioCobertura.obtenerCoberturas();
        model.put("derivacion",derivacion);
//        model.put("pacientes",paciente);
//        model.put("coberturas",coberturas);

        return new ModelAndView("Derivaciones/modificar-derivacion",model);
    }


    @RequestMapping(path = "/modificar-derivacion/editar" , method = RequestMethod.POST)
    public ModelAndView modificarDatosPost(@ModelAttribute("derivacion") Derivacion derivacion, RedirectAttributes attributes){
        derivacion.setFechaDerivacion(new Date());
        servicioDerivacion.modificarDerivacion(derivacion);
        attributes.addFlashAttribute("message","Se modifico la derivacion exitosamente.");
        return new ModelAndView("redirect:/listado-derivacion");
    }


    @RequestMapping(path = "eliminar-derivacion" , method = RequestMethod.POST)
    public ModelAndView eliminarDerivacion(@ModelAttribute("derivacion") Derivacion derivacion,RedirectAttributes attributes){
        servicioDerivacion.eliminarDerivacion(derivacion);
        attributes.addFlashAttribute("message","Se elimino exitosamente.");
        return new ModelAndView("redirect:/listado-derivacion");

    }

    @RequestMapping(path = "historialDerivaciones", method = RequestMethod.GET)
    public ModelAndView historialDerivaciones(HttpServletRequest request) throws ParseException {
        ModelMap map = new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("ID_USUARIO");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaMax = sdf.format(new Date());
        List<Derivacion> derivaciones = servicioDerivacion.filtrarDerivacionesPorFecha(idUsuario, "1900-01-01", fechaMax);
        map.put("derivaciones", derivaciones);
        return new ModelAndView("Derivaciones/historial-derivaciones", map);
    }

    @RequestMapping(path = "filtrarDerivaciones", method = RequestMethod.POST)
    public ModelAndView filtrarDerivaciones(@RequestParam String fechaMin, @RequestParam String fechaMax, HttpServletRequest request) throws ParseException {
        ModelMap map = new ModelMap();
        if(fechaMin.equals("") && fechaMax.equals("")){
            return new ModelAndView("redirect:/historialDerivaciones");
        }
        if(fechaMin.equals("")){
            fechaMin = "1900-01-01";
        }
        if(fechaMax.equals("")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            fechaMax = sdf.format(new Date());
        }
        Long idUsuario = (Long) request.getSession().getAttribute("ID_USUARIO");
        List<Derivacion> derivaciones = servicioDerivacion.filtrarDerivacionesPorFecha(idUsuario, fechaMin, fechaMax);
        map.put("derivaciones", derivaciones);
        return new ModelAndView("Derivaciones/historial-derivaciones", map);
    }
}
