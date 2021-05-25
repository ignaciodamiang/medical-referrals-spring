package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioDerivador {

	Derivador obtenerDerivadorPorUsuario(Usuario usuario);
}
