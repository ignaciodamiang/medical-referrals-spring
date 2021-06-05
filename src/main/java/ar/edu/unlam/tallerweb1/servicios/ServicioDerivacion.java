package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;

import java.util.List;

public interface ServicioDerivacion {
    void guardarDerirvacion(Derivacion derivacion);

    void modificarDerivacion(Derivacion derivacion);

    List<Derivacion> listadoDerivaciones();

    Derivacion verDerivacion(Long id) throws Exception;
    void eliminarDerivacion(Derivacion derivacion);
    List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
}
