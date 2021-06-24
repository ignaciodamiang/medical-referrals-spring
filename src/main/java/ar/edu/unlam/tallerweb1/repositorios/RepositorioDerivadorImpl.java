package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.Derivador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

@Repository("repositorioDerivador")
public class RepositorioDerivadorImpl implements RepositorioDerivador {

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioDerivadorImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public Derivador obtenerDerivadorPorUsuario(Usuario usuario) {
	final Session session = sessionFactory.getCurrentSession();
	return (Derivador) session.createCriteria(Derivador.class)
			.add(Restrictions.eq("usuario", usuario))
			.uniqueResult();
	}

	@Override
	public List<Derivador> obtenerDerivadoresPorCobertura(Cobertura cobertura) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Derivador.class)
				.add(Restrictions.eq("cobertura", cobertura)).list();
	}


}