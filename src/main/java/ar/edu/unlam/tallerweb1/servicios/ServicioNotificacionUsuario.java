package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.springframework.stereotype.Service;

public interface ServicioNotificacionUsuario {

	void guardarNotificacionUsuario(Notificacion notificacion, Long id);
	List<NotificacionUsuario> obtenerNotificacionPorUsuario(HttpServletRequest request);
	Integer obtenerNotificacionesNoLeidas(HttpServletRequest request);
	void marcarComoLeida(Long idNotificacionUsuario);
	NotificacionUsuario mostrarNotificacionUsuario(Long idNotificacionUsuario);
	void guardarNotificacionDerivadores(Cobertura cobertura, Notificacion notificacion);
	void guardarNotificacionAdministrativos(CentroMedico centroMedico, Notificacion notificacion);
}
