package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ServicioDerivacion {
    void guardarDerivacion(Derivacion derivacion, HttpServletRequest request);

    void modificarDerivacion(Derivacion derivacion);

    List<Derivacion> listadoDerivaciones();

    Derivacion verDerivacion(Long id) throws Exception;
    void eliminarDerivacion(Derivacion derivacion);

<<<<<<< HEAD
=======
    List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor);
>>>>>>> 9f70280b77834244d6c26641a6552add2eb35383
    List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
}
