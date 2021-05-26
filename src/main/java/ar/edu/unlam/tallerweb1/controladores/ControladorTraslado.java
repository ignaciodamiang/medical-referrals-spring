package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorTraslado {

    ServicioTraslado servicioTraslado;
    ServicioCentroMedico servicioCentroMedico;

    @Autowired
    public ControladorTraslado(ServicioTraslado servicioTraslado, ServicioCentroMedico servicioCentroMedico)
    {this.servicioTraslado = servicioTraslado;
    this.servicioCentroMedico = servicioCentroMedico;}

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ModelAndView obtenerTraslados(HttpServletRequest request) throws Exception {
        ModelMap map = new ModelMap();
        CentroMedico centroMedico = servicioCentroMedico.obtenerCentroMedicoPorId(
                (Long) request.getSession().getAttribute("ID_CENTROMEDICO"));
        List<Traslado> traslados = servicioTraslado.obtenerTrasladosPorCentroMedico(centroMedico);
        map.put("traslados" ,traslados);
        return new ModelAndView("Traslado/traslados", map);
    }
}
