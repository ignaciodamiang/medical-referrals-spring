package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioDerivador {

	Derivador obtenerDerivadorPorUsuario(Usuario usuario);
}
