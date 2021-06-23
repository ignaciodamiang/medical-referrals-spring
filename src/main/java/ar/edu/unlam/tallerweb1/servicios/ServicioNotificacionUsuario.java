package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.NotificacionUsuario;

public interface ServicioNotificacionUsuario {

	void guardarNotificacionUsuario(Notificacion notificacion, Long id);
	List<NotificacionUsuario> obtenerNotificacionPorUsuario(HttpServletRequest request);
	Integer obtenerNotificacionesNoLeidas(HttpServletRequest request);
}
