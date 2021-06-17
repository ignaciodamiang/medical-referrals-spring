package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
@Repository("repositorioNotificacion")
public class RepositorioNotificacionImpl implements RepositorioNotificacion{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioNotificacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public void guardarNotificacion(Notificacion notificacion) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(notificacion);
	}

	@Override
	public Notificacion mostrarNotificacion(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Notificacion notificacion = session.get(Notificacion.class, id);
		return notificacion;
	}

}
