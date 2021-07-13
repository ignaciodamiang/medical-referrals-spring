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
    @Autowired
    public ServicioSolicitudDerivacionImpl(RepositorioSolicitudDerivacion servicioSoliciturDerivacionDao,RepositorioDerivacion servicioDerivacion,
                                           RepositorioCentroMedico repositorioCentroMedico)
    {this.servicioSoliciturDerivacionDao= servicioSoliciturDerivacionDao;
     this.servicioDerivacion=servicioDerivacion;
     this.repositorioCentroMedico= repositorioCentroMedico;
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
            //Notificacion notificacion = new Notificacion();
            //notificacion.setTitulo("Se ha generado una nueva solicitud de derivación");
            //notificacion.setMensaje("Se ha generado una nueva solicitud de derivación para el centro medico " +solicitudDerivacion.getCentroMedico()
              //      +" perteneciente al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto());
            //notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
            //servicioNotificacion.guardarNotificacion(notificacion);
            //servicioNotificacionUsuario.guardarNotificacionAdministrativos(solicitudDerivacion.getCentroMedico(), notificacion);
        }
    }

    @Override
    public void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        servicioSoliciturDerivacionDao.modificarSolicitudDerivacion(solicitudDerivacion);
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
    public SolicitudDerivacion obetenerSolicitudDerivacionPorId(Long id) {
        return servicioSoliciturDerivacionDao.buscarSolicitudDerivacionPorId(id);
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorDerivacion(Long id) {
        Derivacion derivacion = servicioDerivacion.verDerivacion(id);
        return servicioSoliciturDerivacionDao.obtenerSolicitudesDeDerivacionPorDerivacion(derivacion);
    }
}
