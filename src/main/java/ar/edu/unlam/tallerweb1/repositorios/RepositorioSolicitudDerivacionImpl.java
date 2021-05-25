package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioSolicitudDerivacion")
public class RepositorioSolicitudDerivacionImpl implements RepositorioSolicitudDerivacion {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSolicitudDerivacionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        final Session session= sessionFactory.getCurrentSession();
        session.save(solicitudDerivacion);
    }

    @Override
    public void modificarSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        final Session session= sessionFactory.getCurrentSession();

        session.save(solicitudDerivacion);
    }

    @Override
    public Derivacion buscarSolicitudDerivacionPorId(Integer id) {
        final Session session= sessionFactory.getCurrentSession();
        return (Derivacion)session.createCriteria(Derivacion.class).add(Restrictions.eq("id", id));
    }
}
