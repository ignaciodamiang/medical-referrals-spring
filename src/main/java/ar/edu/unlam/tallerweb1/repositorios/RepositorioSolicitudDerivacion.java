package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.List;

public interface RepositorioSolicitudDerivacion {
    void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    SolicitudDerivacion buscarSolicitudDerivacionPorId(Long id);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion();
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorCentroMedico(CentroMedico centroMedico);

}
