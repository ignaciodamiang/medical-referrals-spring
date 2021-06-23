package ar.edu.unlam.tallerweb1.servicios;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface ServicioNotificacion {
	void guardarNotificacion(Notificacion notificacion);
	Notificacion mostrarNotificacion(Long id) throws Exception;
}
