package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;

public interface RepositorioDerivacion {
    void guardarDerivacion(Derivacion derivacion);
    void modificarDerivacion(Derivacion derivacion);
}
