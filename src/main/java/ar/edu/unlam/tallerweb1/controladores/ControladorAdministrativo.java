package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.servicios.ServicioCentroMedico;
import ar.edu.unlam.tallerweb1.servicios.ServicioDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSolicitudDerivacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorAdministrativo {

    private ServicioSolicitudDerivacion servicioSolicitudDerivacion;
    private ServicioTraslado servicioTraslado;
    private ServicioDerivacion servicioDerivacion;

    @Autowired
    public ControladorAdministrativo(ServicioSolicitudDerivacion servicioSolicitudDerivacion, ServicioTraslado servicioTraslado
                                    ,ServicioDerivacion servicioDerivacion){
        this.servicioSolicitudDerivacion=servicioSolicitudDerivacion;
        this.servicioTraslado=servicioTraslado;
        this.servicioDerivacion=servicioDerivacion;
    }

    @RequestMapping(path = "graficasAdministrativo", method = RequestMethod.GET)
    public ModelAndView graficasAdministrativo(HttpServletRequest request) throws Exception {
        if(!request.getSession().getAttribute("ROL").equals("Administrativo")){
            return new ModelAndView("redirect:/router");
        }
        ModelMap map = new ModelMap();

        Date fechaMax = new Date();
        Date fechaMin = new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01");

        List<Traslado> trasladosRechazados = servicioTraslado.obtenerTrasladosPorCentroMedicoCanceladosPorFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), fechaMin, fechaMax);
        List<Traslado> trasladosFinalizados = servicioTraslado.obtenerTrasladosPorCentroMedicoFinalizadosPorFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), fechaMin, fechaMax);

        List<SolicitudDerivacion> solicitudDerivacionsAceptada = servicioSolicitudDerivacion.obtenerSolicitudesDerivacionAceptadasPorCentroMedicoYFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), fechaMin, fechaMax);
        List<SolicitudDerivacion> solicitudDerivacionsRechazada = servicioSolicitudDerivacion.obtenerSolicitudesDerivacionAceptadasPorCentroMedicoYFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), fechaMin, fechaMax);

        List<Derivacion> derivaciones = servicioDerivacion.obtenerDerivacionesFinalizadasPorCentroMedicoYFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), fechaMin, fechaMax);

        map.put("cantidadTrasladosCancelados", trasladosRechazados.size());
        map.put("cantidadTrasladosFinalizados", trasladosFinalizados.size());
        map.put("cantidadDeTraslados", trasladosRechazados.size() + trasladosFinalizados.size());
        map.put("pacientesIngresados", trasladosFinalizados);
        map.put("pacientesSalidos", derivaciones);
        map.put("cantidadSolicitudesAceptadas", solicitudDerivacionsAceptada.size());
        map.put("cantidadSolicitudesRechazadas", solicitudDerivacionsRechazada.size());
        map.put("cantidadSolicitudes", solicitudDerivacionsAceptada.size() + solicitudDerivacionsRechazada.size());
        return new ModelAndView("Administrativo/grafica", map);
    }

    @RequestMapping(path = "filtrarGraficas", method = RequestMethod.POST)
    public ModelAndView filtrarGrafica(@RequestParam String fechaMin, @RequestParam String fechaMax,
                                            HttpServletRequest request) throws Exception {
        if(!request.getSession().getAttribute("ROL").equals("Administrativo")){
            return new ModelAndView("redirect:/router");
        }
        ModelMap map = new ModelMap();
        if (fechaMin.equals("") && fechaMax.equals("")) {
            return new ModelAndView("redirect:/graficasAdministrativo");
        }
        Date desde;
        Date hasta;
        Date fix;
        if (fechaMin.equals("")) {
            desde = new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01");
        }else{
            desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMin);
        }
        if (fechaMax.equals("")) {
            fix = new Date();
        }else{
            hasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMax);
            fix = new Date(hasta.getTime()+(1000 * 60 * 60 * 24));
        }

        List<Traslado> trasladosRechazados = servicioTraslado.obtenerTrasladosPorCentroMedicoCanceladosPorFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), desde, fix);
        List<Traslado> trasladosFinalizados = servicioTraslado.obtenerTrasladosPorCentroMedicoFinalizadosPorFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), desde, fix);

        List<SolicitudDerivacion> solicitudDerivacionsAceptada = servicioSolicitudDerivacion.obtenerSolicitudesDerivacionAceptadasPorCentroMedicoYFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), desde, fix);
        List<SolicitudDerivacion> solicitudDerivacionsRechazada = servicioSolicitudDerivacion.obtenerSolicitudesDerivacionAceptadasPorCentroMedicoYFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), desde, fix);

        List<Derivacion> derivaciones = servicioDerivacion.obtenerDerivacionesFinalizadasPorCentroMedicoYFecha((Long) request.getSession().getAttribute("ID_CENTROMEDICO"), desde, fix);

        map.put("cantidadTrasladosCancelados", trasladosRechazados.size());
        map.put("cantidadTrasladosFinalizados", trasladosFinalizados.size());
        map.put("cantidadDeTraslados", trasladosRechazados.size() + trasladosFinalizados.size());
        map.put("pacientesIngresados", trasladosFinalizados);
        map.put("pacientesSalidos", derivaciones);
        map.put("cantidadSolicitudesAceptadas", solicitudDerivacionsAceptada.size());
        map.put("cantidadSolicitudesRechazadas", solicitudDerivacionsRechazada.size());
        map.put("cantidadSolicitudes", solicitudDerivacionsAceptada.size() + solicitudDerivacionsRechazada.size());
        return new ModelAndView("Administrativo/grafica", map);
    }
}
