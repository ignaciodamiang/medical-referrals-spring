package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
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
    public ModelAndView derivaciones(){
        ModelMap model = new ModelMap();
        Derivacion derivacion = new Derivacion();
        model.put("eliminarDerivacion",derivacion);
        model.put("derivaciones",servicioDerivacion.listadoDerivaciones());
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
                                          ,@RequestParam("urgente") String urgente){

        Paciente paciente = servicioPaciente.obtenerPacientePorId(idPaciente);
        derivacion.setPaciente(paciente);
        Boolean atributoUrgente = new Boolean(urgente);
        derivacion.setUrgente(atributoUrgente);
        derivacion.setFechaDerivacion(new Date());
        derivacion.setUrgente(true);
        derivacion.setFinalizada(false);


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
}
