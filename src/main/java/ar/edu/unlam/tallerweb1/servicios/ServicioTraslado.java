package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface ServicioTraslado {
    void guardarTraslado(Long idSolicitud);
    void modificarTraslado(Traslado traslado);
    void finalizarTraslado(Long idTraslado, HttpServletRequest request) throws Exception;
    void cancelarTraslado(Long idTraslado, String mensaje) throws MessagingException;
    List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico);
    Traslado obtenerTrasladoPorDerivacion(Long idDerivacion);
    List<Traslado> obtenerTraslados();
    Traslado obtenerTrasladoPorId(Long idTraslado);
    List<Traslado> obtenerTrasladosPorCentroMedicoCanceladosPorFecha(Long idCentroMedico, Date desde, Date hasta) throws Exception;
    List<Traslado> obtenerTrasladosPorCentroMedicoFinalizadosPorFecha(Long idCentroMedico, Date desde, Date hasta) throws Exception;
}
