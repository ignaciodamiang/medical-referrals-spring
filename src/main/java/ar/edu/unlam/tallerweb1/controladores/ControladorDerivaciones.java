package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobertura;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaciente;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class ControladorDerivaciones {
    private ServicioDerivacion servicioDerivacion;
    private ServicioPaciente servicioPaciente;
    private ServicioCobertura servicioCobertura;

    @Autowired
    public ControladorDerivaciones(ServicioDerivacion servicioDerivacion, ServicioPaciente servicioPaciente, ServicioCobertura servicioCobertura){
        this.servicioDerivacion = servicioDerivacion;
        this.servicioPaciente = servicioPaciente;
        this.servicioCobertura = servicioCobertura;
    }

    @RequestMapping(path = "/listado-derivacion")
    public ModelAndView derivaciones(){
        ModelMap model = new ModelMap();
        model.put("derivaciones",servicioDerivacion.listadoDerivaciones());
        return new ModelAndView("Derivaciones/derivaciones",model);
    }

    @RequestMapping(path = "/nueva-derivacion")
    public ModelAndView nuevaDerivacion(){
        ModelMap model = new ModelMap();
        List<Paciente> pacientes = servicioPaciente.obtenerPacientes();
        List<Cobertura> coberturas = servicioCobertura.obtenerCoberturas();
        Derivacion derivacion = new Derivacion();
        model.put("derivacion", derivacion);
        model.put("pacientes",pacientes);
        model.put("coberturas",coberturas);

        return new ModelAndView("Derivaciones/agregar-derivacion",model);
    }


    @RequestMapping(path="agregar-derivacion", method = RequestMethod.POST)
    public ModelAndView agregarDerivacion(Derivacion derivacion, RedirectAttributes attributes){

        derivacion.setFechaDerivacion(new Date());
        derivacion.setUrgente(true);
        derivacion.setEstado("Pendiente");


        servicioDerivacion.guardarDerirvacion(derivacion);
        attributes.addFlashAttribute("message","Se creo la derivación correctamente");
        return new ModelAndView("redirect:listado-derivacion");
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
        derivacion.setEstado("Pendiente");
        servicioDerivacion.modificarDerivacion(derivacion);
        attributes.addFlashAttribute("message","Se modifico la derivacion exitosamente.");
        return new ModelAndView("redirect:/listado-derivacion");
    }
}
