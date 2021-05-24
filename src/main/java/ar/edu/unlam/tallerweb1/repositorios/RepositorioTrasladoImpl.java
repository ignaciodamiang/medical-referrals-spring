package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Traslado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioTraslado")
public class RepositorioTrasladoImpl implements RepositorioTraslado{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioTrasladoImpl (SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public void guardarTraslado(Traslado traslado) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(traslado);
    }

    @Override
    public void modificarTraslado(Traslado traslado){
        final Session session = sessionFactory.getCurrentSession();
        session.update(traslado);
    }
}
