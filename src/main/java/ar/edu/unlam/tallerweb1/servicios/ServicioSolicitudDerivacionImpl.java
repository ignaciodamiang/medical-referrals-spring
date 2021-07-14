package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCentroMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitudDerivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("servicioSolicitudDerivacion")
@Transactional
public class ServicioSolicitudDerivacionImpl implements ServicioSolicitudDerivacion{
    private RepositorioSolicitudDerivacion servicioSoliciturDerivacionDao;
    private RepositorioDerivacion servicioDerivacion;
    private RepositorioCentroMedico repositorioCentroMedico;
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    public ServicioSolicitudDerivacionImpl(RepositorioSolicitudDerivacion servicioSoliciturDerivacionDao,RepositorioDerivacion servicioDerivacion,
                                           RepositorioCentroMedico repositorioCentroMedico, ServicioNotificacion servicioNotificacion)
    {this.servicioSoliciturDerivacionDao= servicioSoliciturDerivacionDao;
     this.servicioDerivacion=servicioDerivacion;
     this.repositorioCentroMedico= repositorioCentroMedico;
     this.servicioNotificacion = servicioNotificacion;
    }


    @Override
    public void guardarSolicitudDerivacion(Long idDerivacion, Long idCentroMedico) {
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        solicitudDerivacion.setFechaCreacion(new Date());
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setConfirmado(false);
        Derivacion derivacion = servicioDerivacion.verDerivacion(idDerivacion);
        CentroMedico centroMedico = repositorioCentroMedico.obtenerCentroMedicoPorId(idCentroMedico);
        if(centroMedico != null && derivacion !=null){
            solicitudDerivacion.setCentroMedico(centroMedico);
            solicitudDerivacion.setDerivacion(derivacion);
            servicioSoliciturDerivacionDao.guardarSolicitudDerivacion(solicitudDerivacion);
            servicioNotificacion.guardarNotificacion(solicitudDerivacion, "G","");
        }
    }

    @Override
    public void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        servicioSoliciturDerivacionDao.modificarSolicitudDerivacion(solicitudDerivacion);
    }

    @Override
    public void aceptarSolicitudDerivacion(Long idSolicitudDerivacion) {
        SolicitudDerivacion solicitudDerivacion = this.obtenerSolicitudDerivacionPorId(idSolicitudDerivacion);
        solicitudDerivacion.setAceptado(true);
        solicitudDerivacion.setId(idSolicitudDerivacion);
        this.modificarSolicitudDerivacion(solicitudDerivacion);
        servicioNotificacion.guardarNotificacion(solicitudDerivacion, "A","");
    }

    @Override
    public void rechazarSolicitudDerivacion(Long idSolicitudDerivacion) {
        SolicitudDerivacion solicitudDerivacion = this.obtenerSolicitudDerivacionPorId(idSolicitudDerivacion);
        solicitudDerivacion.setAceptado(false);
        solicitudDerivacion.setId(idSolicitudDerivacion);
        this.modificarSolicitudDerivacion(solicitudDerivacion);
        servicioNotificacion.guardarNotificacion(solicitudDerivacion, "R","");
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
}
