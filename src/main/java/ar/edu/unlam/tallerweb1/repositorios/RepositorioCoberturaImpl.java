package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioCobertura")
public class RepositorioCoberturaImpl implements RepositorioCobertura{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCoberturaImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public List<Cobertura> obtenerCoberturas() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Cobertura.class).list();
    }

<<<<<<< HEAD
    @Override
    public Cobertura obtenerCoberturaPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Cobertura.class, id);
    }
=======
	@Override
	public Cobertura obtenerCoberturaPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Cobertura.class, id);
	}
>>>>>>> 9f70280b77834244d6c26641a6552add2eb35383
}
