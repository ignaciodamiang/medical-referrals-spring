package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioDerivador {

	Derivador obtenerDerivadorPorUsuario(Usuario usuario);
	List<Derivador> obtenerDerivadoresPorCobertura(Cobertura cobertura);
}
