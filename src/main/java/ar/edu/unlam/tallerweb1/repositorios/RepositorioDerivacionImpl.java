package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Derivacion> listadoDerivaciones() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class).list();
    }

    @Override
    public Derivacion verDerivacion(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Derivacion derivacion = session.get(Derivacion.class,id);
        return derivacion;
    }

    @Override
    public List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class)
                .add(Restrictions.eq("autor", autor)).list();
    }

    @Override
    public void eliminarDerivacion(Derivacion derivacion) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(derivacion);
    }
}
