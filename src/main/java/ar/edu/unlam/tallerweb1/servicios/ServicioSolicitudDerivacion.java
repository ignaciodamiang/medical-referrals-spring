package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ServicioSolicitudDerivacion {
    void guardarSolicitudDerivacion(Long idDerivacion, Long idCentroMedico, String descripcion, HttpServletRequest request);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    void aceptarSolicitudDerivacion(Long idSolicitudDerivacion, HttpServletRequest request);
    void rechazarSolicitudDerivacion(Long idSolicitudDerivacion, HttpServletRequest request);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion();
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorCentroMedico(CentroMedico centroMedico);
    SolicitudDerivacion obtenerSolicitudDerivacionPorId(Long id);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorDerivacion(Long id);
    String generarCodigoSolicitudDerivacion(Long idSolicitudDerivacion);

}
