package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.List;

public interface RepositorioSolicitudDerivacion {
    void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    SolicitudDerivacion buscarSolicitudDerivacionPorId(Integer id);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion();

}
