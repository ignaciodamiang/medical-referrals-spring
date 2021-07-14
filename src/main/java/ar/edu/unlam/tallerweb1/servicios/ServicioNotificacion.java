package ar.edu.unlam.tallerweb1.servicios;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;

public interface ServicioNotificacion {
	void guardarNotificacion(Notificacion notificacion);
	void guardarNotificacion(SolicitudDerivacion solicitudDerivacion, String funcion);
	void guardarNotificacion(Derivacion derivacion, String funcion);
	Notificacion guardarNotificacion(Traslado traslado, String funcion);
	Notificacion mostrarNotificacion(Long id) throws Exception;
}
