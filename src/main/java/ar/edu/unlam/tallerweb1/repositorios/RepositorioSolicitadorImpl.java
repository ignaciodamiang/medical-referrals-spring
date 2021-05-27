package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Solicitador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository("repositorioSolicitador")
public class RepositorioSolicitadorImpl implements RepositorioSolicitador {

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioSolicitadorImpl(SessionFactory session) {
		this.sessionFactory=session;
	}
	
	@Override
	public Solicitador obtenerSolicitadorPorUsuario(Usuario usuario) {
	final Session session = sessionFactory.getCurrentSession();
	return (Solicitador) session.createCriteria(Solicitador.class)
			.add(Restrictions.eq("usuario", usuario))
			.uniqueResult();
	}
}
