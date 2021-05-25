package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Administrativo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioAdministrativo {

	Administrativo obtenerAdministrativoPorUsuario(Usuario usuario);
}
