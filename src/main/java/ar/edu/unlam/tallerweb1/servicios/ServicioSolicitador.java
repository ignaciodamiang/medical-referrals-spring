package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Solicitador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioSolicitador {

	Solicitador obtenerSolicitadorPorUsuario(Usuario usuario);
}
