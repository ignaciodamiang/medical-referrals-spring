package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioDerivacion")
public class RepositorioDerivacionImpl implements RepositorioDerivacion {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDerivacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardarDerivacion(Derivacion derivacion) {
        final Session session =sessionFactory.getCurrentSession();
        session.save(derivacion);
    }

    @Override
    public void modificarDerivacion(Derivacion derivacion) {
        final Session session =sessionFactory.getCurrentSession();
        session.update(derivacion);
    }
}
