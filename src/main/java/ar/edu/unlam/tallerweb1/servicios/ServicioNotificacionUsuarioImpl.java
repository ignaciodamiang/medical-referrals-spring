package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacionUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
@Service("servicioNotificacionUsuario")
@Transactional
public class ServicioNotificacionUsuarioImpl implements ServicioNotificacionUsuario {

	private RepositorioUsuario repositorioUsuario;
	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioNotificacionUsuario repositorioNotificacionUsuario;
	private RepositorioDerivador repositorioDerivador;
	
	@Autowired
	public ServicioNotificacionUsuarioImpl(RepositorioUsuario repositorioUsuario, RepositorioNotificacion repositorioNotificacion, RepositorioNotificacionUsuario repositorioNotificacionUsuario,
										   RepositorioDerivador repositorioDerivador) {
		this.repositorioNotificacion=repositorioNotificacion;
		this.repositorioUsuario=repositorioUsuario;
		this.repositorioNotificacionUsuario=repositorioNotificacionUsuario;
		this.repositorioDerivador = repositorioDerivador;
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

	@Override
	public void marcarComoLeida(Long idNotificación) {
		NotificacionUsuario notificacionUsuario = repositorioNotificacionUsuario.mostrarNotificacionUsuario(idNotificación);
		if(!notificacionUsuario.getLeido()){
			notificacionUsuario.setLeido(true);
			repositorioNotificacionUsuario.modificarNotificacionUsuario(notificacionUsuario);
		}
	}

	@Override
	public NotificacionUsuario mostrarNotificacionUsuario(Long idNotificacionUsuario) {
		return repositorioNotificacionUsuario.mostrarNotificacionUsuario(idNotificacionUsuario);
	}

	@Override
	public void guardarNotificacionDerivadores(Cobertura cobertura, Notificacion notificacion) {
		List<Derivador> derivadores = repositorioDerivador.obtenerDerivadoresPorCobertura(cobertura);
		for (Derivador derivador : derivadores){
			this.guardarNotificacionUsuario(notificacion, derivador.getUsuario().getId());
		}
	}

}