package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import javax.mail.MessagingException;
import java.util.List;

public interface ServicioTraslado {
    void guardarTraslado(Long idSolicitud);
    void modificarTraslado(Traslado traslado);
    void finalizarTraslado(Long idTraslado) throws MessagingException;
    void cancelarTraslado(Long idTraslado, String mensaje) throws MessagingException;
    List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico);
    Traslado obtenerTrasladoPorDerivacion(Long idDerivacion);
    List<Traslado> obtenerTraslados();
    Traslado obtenerTrasladoPorId(Long idTraslado);
}
