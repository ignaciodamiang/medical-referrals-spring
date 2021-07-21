package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.NotificacionUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository("repositorioNotificacionUsuario")
public class RepositorioNotificacionUsuarioImpl implements RepositorioNotificacionUsuario{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioNotificacionUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public List<NotificacionUsuario> obtenerNotificacionPorUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(NotificacionUsuario.class)
				.add(Restrictions.eq("usuario", usuario))
				.addOrder(Order.desc("notificacion"))
				.list();
	}

	@Override
	public void guardarNotificacionUsuario(NotificacionUsuario notiUsuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(notiUsuario);
	}

	@Override
	public List<NotificacionUsuario> obtenerNotificacionesNoLeidasPorUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(NotificacionUsuario.class)
				.add(Restrictions.eq("usuario", usuario))
				.add(Restrictions.eq("leido",false))
				.list();
	}

	@Override
	public void modificarNotificacionUsuario(NotificacionUsuario notificacionUsuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(notificacionUsuario);
	}

	@Override
	public NotificacionUsuario mostrarNotificacionUsuario(Long idNotificacionUsuario) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(NotificacionUsuario.class, idNotificacionUsuario);
	}


}
