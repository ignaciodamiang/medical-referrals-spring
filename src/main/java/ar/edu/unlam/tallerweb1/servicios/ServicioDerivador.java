package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioDerivador {

	Derivador obtenerDerivadorPorUsuario(Usuario usuario);
	List<Derivador> obtenerDerivadoresPorCobertura(Cobertura cobertura);
}
