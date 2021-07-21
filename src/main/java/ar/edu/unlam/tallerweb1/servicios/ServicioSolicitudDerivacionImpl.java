package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCentroMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitudDerivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service("servicioSolicitudDerivacion")
@Transactional
public class ServicioSolicitudDerivacionImpl implements ServicioSolicitudDerivacion{
    private RepositorioSolicitudDerivacion servicioSoliciturDerivacionDao;
    private RepositorioDerivacion servicioDerivacion;
    private RepositorioCentroMedico repositorioCentroMedico;
    private ServicioNotificacion servicioNotificacion;
    private ServicioComentario servicioComentario;
    private ServicioUsuario servicioUsuario;

    @Autowired
    public ServicioSolicitudDerivacionImpl(RepositorioSolicitudDerivacion servicioSoliciturDerivacionDao,RepositorioDerivacion servicioDerivacion,
                                           RepositorioCentroMedico repositorioCentroMedico, ServicioNotificacion servicioNotificacion,
                                           ServicioComentario servicioComentario, ServicioUsuario servicioUsuario)
    {this.servicioSoliciturDerivacionDao= servicioSoliciturDerivacionDao;
     this.servicioDerivacion=servicioDerivacion;
     this.repositorioCentroMedico= repositorioCentroMedico;
     this.servicioNotificacion = servicioNotificacion;
     this.servicioComentario = servicioComentario;
     this.servicioUsuario = servicioUsuario;
    }


    @Override
    public void guardarSolicitudDerivacion(Long idDerivacion, Long idCentroMedico, String descripcion, HttpServletRequest request) {
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        solicitudDerivacion.setFechaCreacion(new Date());
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setConfirmado(false);
        Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
        CentroMedico centroMedico = repositorioCentroMedico.obtenerCentroMedicoPorId(idCentroMedico);
        if(centroMedico != null && derivacion !=null){
            Usuario usuario = servicioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
            solicitudDerivacion.setCentroMedico(centroMedico);
            solicitudDerivacion.setDerivacion(derivacion);
            solicitudDerivacion.setDescripcion(descripcion);
            servicioSoliciturDerivacionDao.guardarSolicitudDerivacion(solicitudDerivacion);
            solicitudDerivacion.setCodigo(this.generarCodigoSolicitudDerivacion(solicitudDerivacion.getId()));
            this.modificarSolicitudDerivacion(solicitudDerivacion);
            servicioNotificacion.guardarNotificacion(solicitudDerivacion, "G","");
            servicioComentario.guardarComentarioSolicitudDerivacion(solicitudDerivacion,"",usuario,"G");
        }
    }

    @Override
    public void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        servicioSoliciturDerivacionDao.modificarSolicitudDerivacion(solicitudDerivacion);
    }

    @Override
    public void aceptarSolicitudDerivacion(Long idSolicitudDerivacion,HttpServletRequest request, String mensaje) {
        Usuario usuario = servicioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        SolicitudDerivacion solicitudDerivacion = this.obtenerSolicitudDerivacionPorId(idSolicitudDerivacion);
        solicitudDerivacion.setAceptado(true);
        solicitudDerivacion.setId(idSolicitudDerivacion);
        this.modificarSolicitudDerivacion(solicitudDerivacion);
        servicioNotificacion.guardarNotificacion(solicitudDerivacion, "A","");
        servicioComentario.guardarComentarioSolicitudDerivacion(solicitudDerivacion, mensaje ,usuario,"A" );
    }

    @Override
    public void rechazarSolicitudDerivacion(Long idSolicitudDerivacion,HttpServletRequest request, String mensaje) {
        Usuario usuario = servicioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        SolicitudDerivacion solicitudDerivacion = this.obtenerSolicitudDerivacionPorId(idSolicitudDerivacion);
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setId(idSolicitudDerivacion);
        this.modificarSolicitudDerivacion(solicitudDerivacion);
        servicioNotificacion.guardarNotificacion(solicitudDerivacion, "R","");
        servicioComentario.guardarComentarioSolicitudDerivacion(solicitudDerivacion, mensaje ,usuario,"R" );
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion() {
       return servicioSoliciturDerivacionDao.obtenerSolicitudesDeDerivacion();
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorCentroMedico(CentroMedico centroMedico) {
        return servicioSoliciturDerivacionDao.obtenerSolicitudesDeDerivacionPorCentroMedico(centroMedico);
    }

    @Override
    public SolicitudDerivacion obtenerSolicitudDerivacionPorId(Long id) {
        return servicioSoliciturDerivacionDao.buscarSolicitudDerivacionPorId(id);
    }

    @Override
            public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorDerivacion(Long id) {
        Derivacion derivacion = servicioDerivacion.verDerivacion(id);
        return servicioSoliciturDerivacionDao.obtenerSolicitudesDeDerivacionPorDerivacion(derivacion);
    }

    @Override
    public String generarCodigoSolicitudDerivacion(Long idSolicitudDerivacion) {
        String prefix = "SOL";
        Long code = 100000L + idSolicitudDerivacion;
        return prefix+code;
    }
}
