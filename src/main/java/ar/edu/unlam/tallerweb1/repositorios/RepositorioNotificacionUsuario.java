package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.NotificacionUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioNotificacionUsuario {

	List <NotificacionUsuario> obtenerNotificacionPorUsuario(Usuario usuario);
	void guardarNotificacionUsuario(NotificacionUsuario notiUsuario);
}
