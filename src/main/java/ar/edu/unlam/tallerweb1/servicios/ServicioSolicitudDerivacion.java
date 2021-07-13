package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.List;

public interface ServicioSolicitudDerivacion {
    void guardarSolicitudDerivacion(Long idDerivacion, Long idCentroMedico);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion();
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorCentroMedico(CentroMedico centroMedico);
    SolicitudDerivacion obetenerSolicitudDerivacionPorId(Long id);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorDerivacion(Long id);
}
