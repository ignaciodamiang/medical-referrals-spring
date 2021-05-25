package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
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
    @Autowired
    public ServicioSolicitudDerivacionImpl(RepositorioSolicitudDerivacion servicioSoliciturDerivacionDao){this.servicioSoliciturDerivacionDao= servicioSoliciturDerivacionDao;}


    @Override
    public void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        Date now = new Date();
        solicitudDerivacion.setFechaCreacion(now);
        servicioSoliciturDerivacionDao.guardarSolicitudDerivacion(solicitudDerivacion);
    }

    @Override
    public void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        servicioSoliciturDerivacionDao.guardarSolicitudDerivacion(solicitudDerivacion);
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion() {
       return servicioSoliciturDerivacionDao.obtenerSolicitudesDeDerivacion();
    }
}
