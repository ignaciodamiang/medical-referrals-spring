package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ServicioDerivacion {
    void guardarDerivacion(Derivacion derivacion, HttpServletRequest request);

    void modificarDerivacion(Derivacion derivacion);

    List<Derivacion> listadoDerivaciones();

    Derivacion verDerivacion(Long id) throws Exception;
    void eliminarDerivacion(Derivacion derivacion);

    List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor);
    List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
    List<Derivacion> filtrarDerivacionesPorFecha(Long idUsuario, String fechaMin, String fechaMax) throws ParseException;
}
