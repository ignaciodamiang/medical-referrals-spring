package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
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

    @Autowired
    public ControladorSolicitudDerivaciones
            (ServicioSolicitudDerivacion servicio)
    {this.servicioSolicitudDerivacion=servicio;}

    @RequestMapping("/solicitudes-derivaciones")
    public ModelAndView mostrarSolicitudesDerivaciones(){
        ModelMap modelo = new ModelMap();
        CentroMedico c= new CentroMedico();
        c.setId(2l);
        c.setNombre("Santojani");
        c.setDireccion("pilar 213");

        Paciente p = new Paciente();
        p.setDocumento(94507958);
        p.setNombreCompleto("jose machicado");

        Derivacion d = new Derivacion();
        d.setEstado("abierto derivacion");
        d.setUrgente(true);
        d.setPaciente(p);

        SolicitudDerivacion soli = new SolicitudDerivacion();
        soli.setDerivacion(d);
        soli.setConfirmado(true);
        soli.setCentroMedico(c);
        Date now =new Date();
        soli.setFechaCreacion(now);

       // no se puede agregar ya que necesitamos que las otras entidades esten en la bdd
      // servicioSolicitudDerivacion.guardarSolicitudDerivacion(soli);

        List<SolicitudDerivacion> lista = servicioSolicitudDerivacion.obtenerSolicitudesDeDerivacion();
        //solo agrego a la lista
        lista.add(soli);
        modelo.put("listaSolicitudesDerivaciones",lista);
        return new ModelAndView("/solicitud-derivaciones/solicitud-derivaciones",modelo);
    }
}
