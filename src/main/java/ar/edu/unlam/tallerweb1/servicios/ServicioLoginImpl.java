package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioAdministrativo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Administrativo;
import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Solicitador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario servicioLoginDao;
	private RepositorioSolicitador servicioSolicitadorDao;
	private RepositorioDerivador servicioDerivadorDao;
	private RepositorioAdministrativo servicioAdministrativoDao;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao,
			RepositorioSolicitador servicioSolicitadorDao,
			RepositorioDerivador servicioDerivadorDao,
			RepositorioAdministrativo servicioAdministrativoDao){
		this.servicioLoginDao = servicioLoginDao;
		this.servicioSolicitadorDao=servicioSolicitadorDao;
		this.servicioDerivadorDao=servicioDerivadorDao;
		this.servicioAdministrativoDao=servicioAdministrativoDao;
	}

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}

	@Override
	public Usuario loguearse(Usuario usuario, HttpServletRequest request) {
		Usuario usuarioObtenido = consultarUsuario(usuario);
		switch (usuarioObtenido.getRol()) {
		case "Administrativo": {
			Administrativo usuarioAdministrativo = servicioAdministrativoDao.obtenerAdministrativoPorUsuario(usuarioObtenido);
			if (usuarioAdministrativo!=null) {
				request.getSession().setAttribute("ID_ADMINISTRATIVO", usuarioAdministrativo.getId());
				request.getSession().setAttribute("ID_CENTROMEDICO", usuarioAdministrativo.getCentroMedico().getId());
			}
			break;
		}
		case "Derivador":{
			Derivador usuarioDerivador = servicioDerivadorDao.obtenerDerivadorPorUsuario(usuarioObtenido);
			if (usuarioDerivador!=null) {
				request.getSession().setAttribute("ID_DERIVADOR", usuarioDerivador.getId());
				request.getSession().setAttribute("ID_COBERTURA", usuarioDerivador.getCobertura().getId());
			}
			break;
		}
		case "Solicitador": {
			Solicitador usuarioSolicitador = servicioSolicitadorDao.obtenerSolicitadorPorUsuario(usuarioObtenido);
			if (usuarioSolicitador!=null) {
				request.getSession().setAttribute("ID_SOLICITADOR", usuarioSolicitador.getId());
			}
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + usuarioObtenido.getRol());
		}
		return usuarioObtenido;
	}

}
