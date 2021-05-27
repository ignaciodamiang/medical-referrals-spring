package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.List;

public interface ServicioSolicitudDerivacion {
    void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion();
    SolicitudDerivacion obetenerSolicitudDerivacionPorId(Long id);
}
