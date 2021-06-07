package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaciente;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPaciente {

    private ServicioPaciente servicioPaciente;
    private ServicioDerivacion servicioDerivacion;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ControladorPaciente(ServicioPaciente servicioPaciente, ServicioDerivacion servicioDerivacion, ServicioUsuario servicioUsuario){
    	this.servicioPaciente = servicioPaciente;
    	this.servicioDerivacion = servicioDerivacion;
    	this.servicioUsuario=servicioUsuario;
	}

    @RequestMapping(path = "/BuscarPaciente")
    public ModelAndView irABuscarPaciente(HttpServletRequest request){
        ModelMap map = new ModelMap();
        Usuario autor = servicioUsuario.consultarUsuarioPorId((Long)request.getSession().getAttribute("ID_SOLICITADOR"));
        //String solicitador= request.getSession().getAttribute("ID_SOLICITADOR");
        List<Derivacion> derivaciones = servicioDerivacion.obtenerDerivacionesPorAutor(autor);
        map.put("derivaciones", derivaciones);
        return new ModelAndView("Paciente/buscarPaciente", map);
    }

    @RequestMapping(path = "/ObtenerPaciente", method = RequestMethod.POST)
        public ModelAndView ABuscarPaciente(@RequestParam ("documento") Integer documento, HttpServletRequest request){
        ModelMap map = new ModelMap();
        Paciente pacienteObtenido = servicioPaciente.obtenerPacientePorDocumento(documento);
        if (pacienteObtenido != null){
            map.put("paciente", pacienteObtenido);
            map.put("idPaciente", pacienteObtenido.getId());
        }else{
            map.put("error","No se encontro al paciente");
        }
        return new ModelAndView("Paciente/buscarPaciente", map);
    }
}
