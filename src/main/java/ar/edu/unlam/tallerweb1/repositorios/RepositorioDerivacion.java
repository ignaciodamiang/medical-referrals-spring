package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;

import java.util.List;

public interface RepositorioDerivacion {
    void guardarDerivacion(Derivacion derivacion);
    void modificarDerivacion(Derivacion derivacion);
    List <Derivacion> listadoDerivaciones();

    Derivacion verDerivacion(long id);

    void eliminarDerivacion(Derivacion derivacion);
}
