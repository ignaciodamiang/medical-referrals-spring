package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.RequerimientosMedicos;
import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioRequerimientosMedicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRequerimientosMedicos {

    private ServicioRequerimientosMedicos servicioRequerimientosMedicos;
    private ServicioCentroMedico servicioCentroMedico;
    private ServicioNotificacionUsuario servicioNotificacionUsuario;

    @Autowired
    public ControladorRequerimientosMedicos(ServicioRequerimientosMedicos servicioRequerimientosMedicos, ServicioCentroMedico servicioCentroMedico,
                                            ServicioNotificacionUsuario servicioNotificacionUsuario){
        this.servicioRequerimientosMedicos = servicioRequerimientosMedicos;
        this.servicioCentroMedico = servicioCentroMedico;
        this.servicioNotificacionUsuario = servicioNotificacionUsuario;
    }

    @RequestMapping("/RequerimientosMedicos")
    public ModelAndView irARequerimientosMedicos(HttpServletRequest request) throws Exception {
        ModelMap map = new ModelMap();
        CentroMedico centroMedico = servicioCentroMedico.obtenerCentroMedicoPorId((Long)request.getSession().getAttribute("ID_CENTROMEDICO"));
        map.put("CentroMedico", centroMedico);
        map.put("Requerimientos", centroMedico.getRequerimientosMedicos());
        map.put("cantNotificacion",servicioNotificacionUsuario.obtenerNotificacionesNoLeidas(request));
        return new ModelAndView("RequerimientosMedicos/requerimientosMedicosCentroMedico", map);
    }

    @RequestMapping("/ModificarRequerimientosMedicos/{idCentroMedico}")
    public ModelAndView modificarRequerimientosMedicos(@PathVariable("idCentroMedico") Long idCentroMedico,
           @RequestParam(name = "tomografo",defaultValue = "false") Boolean tomografo,
           @RequestParam(name = "traumatologoGuardia",defaultValue = "false") Boolean traumatologoGuardia,
           @RequestParam(name = "cirujanoGuardia",defaultValue = "false") Boolean cirujanoGuardia,
           @RequestParam(name = "cardiologoGuardia",defaultValue = "false") Boolean cardiologoGuardia) throws Exception {
        RequerimientosMedicos requerimientosMedicos = servicioCentroMedico.obtenerCentroMedicoPorId(idCentroMedico).getRequerimientosMedicos();
        requerimientosMedicos.setTomografo(tomografo);
        requerimientosMedicos.setTraumatologoDeguardia(traumatologoGuardia);
        requerimientosMedicos.setCardiologoSeGuardia(cardiologoGuardia);
        requerimientosMedicos.setCirujanoDeGuardia(cirujanoGuardia);
        servicioRequerimientosMedicos.modificarRequerimientosMedicos(requerimientosMedicos);
        return new ModelAndView("redirect:/router");
    }
}
