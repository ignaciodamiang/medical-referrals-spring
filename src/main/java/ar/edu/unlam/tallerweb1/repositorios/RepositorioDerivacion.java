package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.Date;
import java.util.List;

public interface RepositorioDerivacion {
    void guardarDerivacion(Derivacion derivacion);
    void modificarDerivacion(Derivacion derivacion);
    List <Derivacion> listadoDerivaciones();

    void eliminarDerivacion(Derivacion derivacion);
    Derivacion verDerivacion(Long id);
	List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);

    List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor);
    List<Derivacion> filtrarDerivacionesPorFecha(Date fechaMin, Date fechaMax, Usuario autor);
}
