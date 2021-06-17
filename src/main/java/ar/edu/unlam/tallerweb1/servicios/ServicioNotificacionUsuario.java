package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.NotificacionUsuario;

public interface ServicioNotificacionUsuario {

	void guardarNotificacionUsuario(Notificacion notificacion, HttpServletRequest request);
	List<NotificacionUsuario> obtenerNotificacionPorUsuario(HttpServletRequest request);
}
