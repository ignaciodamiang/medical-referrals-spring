package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.NotificacionUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioNotificacionUsuario {

	List <NotificacionUsuario> obtenerNotificacionPorUsuario(Usuario usuario);
	void guardarNotificacionUsuario(NotificacionUsuario notiUsuario);
	List<NotificacionUsuario> obtenerNotificacionesNoLeidasPorUsuario(Usuario usuario);
	void modificarNotificacionUsuario(NotificacionUsuario notificacionUsuario);
	NotificacionUsuario mostrarNotificacionUsuario(Long idNotificacion);
}
