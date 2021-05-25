package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudDerivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class ControladorSolicitudDerivaciones {
    private ServicioSolicitudDerivacion servicioSolicitudDerivacion;
    private ServicioDerivacion servicioDerivacion;
    private ServicioCentroMedico servicioCentroMedico;

    @Autowired
    public ControladorSolicitudDerivaciones
            (ServicioSolicitudDerivacion servicio, ServicioDerivacion servicioDerivacion, ServicioCentroMedico servicioCentroMedico) {
        this.servicioSolicitudDerivacion = servicio;
        this.servicioDerivacion = servicioDerivacion;
        this.servicioCentroMedico = servicioCentroMedico;
    }

    @RequestMapping("/solicitudes-derivaciones")
    public ModelAndView mostrarSolicitudesDerivaciones() {
        ModelMap modelo = new ModelMap();
        // no se puede agregar ya que necesitamos que las otras entidades esten en la bdd
        // servicioSolicitudDerivacion.guardarSolicitudDerivacion(soli);
        List<SolicitudDerivacion> lista = servicioSolicitudDerivacion.obtenerSolicitudesDeDerivacion();
        modelo.put("listaSolicitudesDerivaciones", lista);
        return new ModelAndView("/solicitud-derivaciones/solicitud-derivaciones", modelo);
    }

    @RequestMapping("/nueva-solicitud-derivacion")
    public ModelAndView nuevaSolicitudDerivacion() {
        ModelMap model = new ModelMap();
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        model.put("derivaciones", servicioDerivacion.listadoDerivaciones());
        model.put("centrosMedicos", servicioCentroMedico.obtenerCentrosMedicos());
        model.put("solicitudDerivacion", solicitudDerivacion);
        return new ModelAndView("/solicitud-derivaciones/agregar-solicitud-derivacion", model);
    }

}