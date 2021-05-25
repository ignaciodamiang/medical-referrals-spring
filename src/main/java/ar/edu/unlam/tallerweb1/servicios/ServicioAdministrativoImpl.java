package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Administrativo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAdministrativo;
import ar.edu.unlam.tallerweb1.repositorios.ServicioAdministrativo;
@Service
public class ServicioAdministrativoImpl implements ServicioAdministrativo{

	private RepositorioAdministrativo repositorioAdministrativo;

	@Autowired
	public ServicioAdministrativoImpl(RepositorioAdministrativo repositorioAdministrativo) {
		this.repositorioAdministrativo=repositorioAdministrativo;
	}
	@Override
	public Administrativo obtenerAdministrativoPorUsuario(Usuario usuario) {
		return repositorioAdministrativo.obtenerAdministrativoPorUsuario(usuario);
	}
	
}
