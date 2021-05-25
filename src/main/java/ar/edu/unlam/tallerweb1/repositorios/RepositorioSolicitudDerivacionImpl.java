package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        session.update(solicitudDerivacion);
    }

    @Override
    public SolicitudDerivacion buscarSolicitudDerivacionPorId(Integer id) {
        final Session session= sessionFactory.getCurrentSession();
        return (SolicitudDerivacion)session.createCriteria(SolicitudDerivacion.class).add(Restrictions.eq("id", id));
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion() {
        final Session session= sessionFactory.getCurrentSession();
        List<SolicitudDerivacion> solicitudesDeDerivacion = session.createCriteria(SolicitudDerivacion.class)
                .addOrder(Order.asc("fechaCreacion"))
                .list();
        return solicitudesDeDerivacion;
    }
}
