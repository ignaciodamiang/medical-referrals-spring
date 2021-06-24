package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Administrativo;
import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioAdministrativo {

	Administrativo obtenerAdministrativoPorUsuario(Usuario usuario);
	List<Administrativo> obtenerArdministrativoPorCentroMedico(CentroMedico centroMedico);
}
