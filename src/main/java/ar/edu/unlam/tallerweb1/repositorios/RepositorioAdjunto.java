package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Adjunto;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.List;

public interface RepositorioAdjunto {
    void guardarAdjunto(Adjunto adjuntoDerivacion);
    List<Adjunto> obtenerAdjuntosPorDerivacion(Derivacion derivacion);
    List<Adjunto> obtenerAdjuntosPorSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
}
