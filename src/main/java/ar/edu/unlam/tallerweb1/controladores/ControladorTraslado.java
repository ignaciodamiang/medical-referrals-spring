package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorTraslado {

    ServicioTraslado servicioTraslado;
    ServicioCentroMedico servicioCentroMedico;
    ServicioSolicitudDerivacion serviciosolicitudDerivacion;

    @Autowired
    public ControladorTraslado(ServicioTraslado servicioTraslado, ServicioCentroMedico servicioCentroMedico, ServicioSolicitudDerivacion solicitudDerivacion)
    {this.servicioTraslado = servicioTraslado;
    this.servicioCentroMedico = servicioCentroMedico;
    this.serviciosolicitudDerivacion = solicitudDerivacion;}

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView obtenerTraslados(HttpServletRequest request) throws Exception {
        ModelMap map = new ModelMap();
        CentroMedico centroMedico = servicioCentroMedico.obtenerCentroMedicoPorId(
                (Long) request.getSession().getAttribute("ID_CENTROMEDICO"));
        List<Traslado> traslados = servicioTraslado.obtenerTrasladosPorCentroMedico(centroMedico);
        map.put("" ,traslados);
        return new ModelAndView("Traslado/traslados", map);
    }

    @RequestMapping(path = "/crearTraslado/{idSolicitud}", method = RequestMethod.GET)
    public ModelAndView crearTraslado(@PathVariable Long idSolicitud){
        SolicitudDerivacion solicitudDerivacion = serviciosolicitudDerivacion.obetenerSolicitudDerivacionPorId(idSolicitud);
        Traslado traslado = new Traslado();
        traslado.setCentroMedico(solicitudDerivacion.getCentroMedico());
        traslado.setDerivacion(solicitudDerivacion.getDerivacion());
        servicioTraslado.guardarTraslado(traslado);
        return new ModelAndView("redirect:/traslados");
    }

    @RequestMapping(path = "/traslados", method = RequestMethod.GET)
    public ModelAndView verTraslados(){
        List<Traslado> traslados = servicioTraslado.obtenerTraslados();
        ModelMap map = new ModelMap();
        map.put("traslados", traslados);
        return new ModelAndView("Traslado/traslados", map);
    }

    @RequestMapping(path = "/ver-traslado/{idDerivacion}", method = RequestMethod.GET)
    public ModelAndView verTraslados(@PathVariable Long idDerivacion){
        Traslado traslado = servicioTraslado.obtenerTrasladoPorDerivacion(idDerivacion);
        ModelMap map = new ModelMap();
        map.put("traslado", traslado);
        return new ModelAndView("Traslado/ver-traslado", map);
    }

}
