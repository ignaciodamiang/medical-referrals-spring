package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

public interface ServicioSolicitudDerivacion {
    void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
}
