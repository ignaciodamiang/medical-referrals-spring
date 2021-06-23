package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.NotificacionUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacionUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
@Service("servicioNotificacionUsuario")
@Transactional
public class ServicioNotificacionUsuarioImpl implements ServicioNotificacionUsuario {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioNotificacionUsuario repositorioNotificacionUsuario;
	
	@Autowired
	public ServicioNotificacionUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioNotificacion repositorioNotificacion, RepositorioNotificacionUsuario repositorioNotificacionUsuario) {
		this.repositorioNotificacion=repositorioNotificacion;
		this.repositorioUsuario=repositorioUsuario;
		this.repositorioNotificacionUsuario=repositorioNotificacionUsuario;
	}
	@Override
	public void guardarNotificacionUsuario(Notificacion notificacion, Long idUsuario) {
		NotificacionUsuario notiUsuario = new NotificacionUsuario();
		Usuario usuario = repositorioUsuario.obtenerUsuarioPorId(idUsuario);
		notiUsuario.setNotificacion(notificacion);
		notiUsuario.setUsuario(usuario);
		notiUsuario.setLeido(false);
		repositorioNotificacionUsuario.guardarNotificacionUsuario(notiUsuario);
	}

	@Override
	public List<NotificacionUsuario> obtenerNotificacionPorUsuario(HttpServletRequest request) {
		Usuario usuario = repositorioUsuario.obtenerUsuarioPorId((Long)request.getSession().getAttribute("ID_USUARIO"));
		return repositorioNotificacionUsuario.obtenerNotificacionPorUsuario(usuario);
	}

	@Override
	public Integer obtenerNotificacionesNoLeidas(HttpServletRequest request) {
		Usuario usuario = repositorioUsuario.obtenerUsuarioPorId((Long)request.getSession().getAttribute("ID_USUARIO"));
		return repositorioNotificacionUsuario.obtenerNotificacionesNoLeidasPorUsuario(usuario).size();
	}

}
