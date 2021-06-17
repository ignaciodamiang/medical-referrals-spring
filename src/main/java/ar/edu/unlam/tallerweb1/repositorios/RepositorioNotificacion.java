package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioNotificacion {
	void guardarNotificacion(Notificacion notificacion);
	Notificacion mostrarNotificacion(Long id);
}
