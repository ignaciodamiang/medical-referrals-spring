package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface RepositorioSolicitudDerivacion {
    void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
    SolicitudDerivacion buscarSolicitudDerivacionPorId(Long id);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion();
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorCentroMedico(CentroMedico centroMedico);
    List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorDerivacion(Derivacion derivacion);
    List<SolicitudDerivacion> obtenerSolicitudesDerivacionAceptadasPorCentroMedicoYFecha(CentroMedico centroMedico, Date desde, Date hasta);
    List<SolicitudDerivacion> obtenerSolicitudesDerivacionRechazadasPorCentroMedicoYFecha(CentroMedico centroMedico, Date desde, Date hasta);
}
