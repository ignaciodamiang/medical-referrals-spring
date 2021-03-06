package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.Administrativo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

@Repository("repositorioAdministrativo")
public class RepositorioAdministrativoImpl implements RepositorioAdministrativo {

	private SessionFactory sessionFactory;
	@Autowired
	public RepositorioAdministrativoImpl(SessionFactory session) {
		this.sessionFactory=session;
	}
	
	
	@Override
	public Administrativo obtenerAdministrativoPorUsuario(Usuario usuario) {
	final Session session = sessionFactory.getCurrentSession();
	return (Administrativo) session.createCriteria(Administrativo.class)
			.add(Restrictions.eq("usuario", usuario))
			.uniqueResult();
	}

	@Override
	public List<Administrativo> obtenerArdministrativoPorCentroMedico(CentroMedico centroMedico) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Administrativo.class)
				.add(Restrictions.eq("centroMedico", centroMedico)).list();
	}
}
