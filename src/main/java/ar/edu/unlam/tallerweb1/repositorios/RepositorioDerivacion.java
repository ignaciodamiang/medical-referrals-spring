package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioDerivacion {
    void guardarDerivacion(Derivacion derivacion);
    void modificarDerivacion(Derivacion derivacion);
    List <Derivacion> listadoDerivaciones();

    void eliminarDerivacion(Derivacion derivacion);
    Derivacion verDerivacion(Long id);
<<<<<<< HEAD

    List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
=======
	List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);

    List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor);
>>>>>>> 9f70280b77834244d6c26641a6552add2eb35383
}
