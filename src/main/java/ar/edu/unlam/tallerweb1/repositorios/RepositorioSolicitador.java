package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Solicitador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioSolicitador {

	Solicitador obtenerSolicitadorPorUsuario(Usuario usuario);
}
