package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPaciente {

    private ServicioPaciente servicioPaciente;

    @Autowired
    public ControladorPaciente(ServicioPaciente servicioPaciente){this.servicioPaciente = servicioPaciente;}

    @RequestMapping(path = "/BuscarPaciente")
    public ModelAndView irABuscarPaciente(){
        ModelMap map = new ModelMap();
        return new ModelAndView("Paciente/buscarPaciente", map);
    }

    @RequestMapping(path = "/ObtenerPaciente", method = RequestMethod.POST)
        public ModelAndView ABuscarPaciente(@RequestParam ("documento") Integer documento, HttpServletRequest request){
        ModelMap map = new ModelMap();
        Paciente pacienteObtenido = servicioPaciente.obtenerPacientePorDocumento(documento);
        if (pacienteObtenido != null){
            map.put("paciente", pacienteObtenido);
        }else{
            map.put("error","No se encontro al paciente");
        }

        return new ModelAndView("Paciente/buscarPaciente", map);
    }
}
