package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
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
    Derivacion verDerivacionPorCodigo(String codigo);
	List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
    List<Derivacion> derivacionesPorCoberturaYFechaFinalizadasYCanceladas(Date desde, Date fix, Cobertura cobertura);
    List<Derivacion> obtenerDerivacionesFinalizadasPorCentroMedicoYFecha(CentroMedico centroMedico, Date desde, Date hasta);
    List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor);
    List<Derivacion> filtrarDerivacionesFinalizadasYCanceladasPorFechaYUsuario(Date fechaMin, Date fechaMax, Usuario autor);

    List<Derivacion> filtrarDerivacionesPorCentroMedicoYFechaFinalizadasYCanceladas(Date fechaMin, Date fechaMax, CentroMedico centroMedico);
}
