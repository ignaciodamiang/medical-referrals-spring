package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.RequerimientosMedicos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ServicioDerivacion {
    void guardarDerivacion(Derivacion derivacion , Long idCobertura, HttpServletRequest request, Long idPaciente, RequerimientosMedicos requerimientosMedicos, Boolean urgente, String ubicacionPaciente) throws Exception;

    void modificarDerivacion(Derivacion derivacion);

    void finalizarDerivacion(Derivacion derivacion,HttpServletRequest request );

    void cancelarDerivacion(Long idDerivacion, String mensaje, HttpServletRequest request) throws Exception;

    void derivacionEnTraslado(Derivacion derivacion,HttpServletRequest request);

    List<Derivacion> listadoDerivaciones();

    Derivacion verDerivacion(Long id) throws Exception;
    void eliminarDerivacion(Derivacion derivacion);
    Derivacion obtenerDerivacionPorCodigo(String codigo);
    List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor);
    List<Derivacion> derivacionesPorCobertura(Cobertura cobertura);
    List<Derivacion> derivacionesPorCoberturaYFechaFinalizadasYCanceladas(HttpServletRequest request, String fechaMin, String fechaMax) throws ParseException;
    List<Derivacion> filtrarDerivacionesFinalizadasYCanceladasPorFechaYUsuario(Long idUsuario, String fechaMin, String fechaMax) throws ParseException;

    List<Derivacion> derivacionesPorCentroMedicoYFechaFinalizadasYCanceladas(HttpServletRequest request, String fechaMin, String fechaMax) throws Exception;
    void guardarDerivacionCentroMedico(Derivacion derivacion,HttpServletRequest request, Long idPaciente, RequerimientosMedicos requerimientosMedicos, Boolean urgente, String ubicacionPaciente) throws Exception;
    String generarCodigoDerivacion(Long idDerivacion);
}
