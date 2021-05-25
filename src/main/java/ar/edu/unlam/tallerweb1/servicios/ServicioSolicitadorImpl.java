package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Solicitador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitador;
@Service("servicioSolicitador")
public class ServicioSolicitadorImpl implements ServicioSolicitador {

	private RepositorioSolicitador repositorioSolicitador;
	@Autowired
	public ServicioSolicitadorImpl(RepositorioSolicitador repositorioSolicitador) {
		this.repositorioSolicitador= repositorioSolicitador;
	}
	@Override
	public Solicitador obtenerSolicitadorPorUsuario(Usuario usuario) {
		return repositorioSolicitador.obtenerSolicitadorPorUsuario(usuario);
	}

}
