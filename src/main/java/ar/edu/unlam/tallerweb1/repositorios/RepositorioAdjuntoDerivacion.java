package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.AdjuntoDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;

import java.util.List;

public interface RepositorioAdjuntoDerivacion {
    void guardarAdjunto(AdjuntoDerivacion adjuntoDerivacion);
    List<AdjuntoDerivacion> obtenerAdjuntosPorDerivacion(Derivacion derivacion);
}
