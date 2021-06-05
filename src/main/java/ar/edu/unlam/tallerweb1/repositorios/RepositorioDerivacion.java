package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;

import java.util.List;

public interface RepositorioDerivacion {
    void guardarDerivacion(Derivacion derivacion);
    void modificarDerivacion(Derivacion derivacion);
    List <Derivacion> listadoDerivaciones();

    void eliminarDerivacion(Derivacion derivacion);
    Derivacion verDerivacion(Long id);
	List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
}
