package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivador;

import java.util.List;

@Service("servicioDerivador")
public class ServicioDerivadorImpl implements ServicioDerivador{

	private RepositorioDerivador repositorioDerivador;
	
	@Autowired
	public ServicioDerivadorImpl(RepositorioDerivador repositorioDerivador) {
		this.repositorioDerivador=repositorioDerivador;
	}
	
	@Override
	public Derivador obtenerDerivadorPorUsuario(Usuario usuario) {
		return repositorioDerivador.obtenerDerivadorPorUsuario(usuario);
	}

	@Override
	public List<Derivador> obtenerDerivadoresPorCobertura(Cobertura cobertura) {
		return repositorioDerivador.obtenerDerivadoresPorCobertura(cobertura);
	}


}
