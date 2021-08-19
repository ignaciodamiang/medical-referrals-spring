package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;
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
    public SolicitudDerivacion buscarSolicitudDerivacionPorId(Long id) {
        final Session session= sessionFactory.getCurrentSession();
        return session.get(SolicitudDerivacion.class, id);
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacion() {
        final Session session= sessionFactory.getCurrentSession();
        List<SolicitudDerivacion> solicitudesDeDerivacion = session.createCriteria(SolicitudDerivacion.class)
                .addOrder(Order.asc("fechaCreacion"))
                .list();
        return solicitudesDeDerivacion;
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorCentroMedico(CentroMedico centroMedico) {
        final Session session= sessionFactory.getCurrentSession();
        List<SolicitudDerivacion> solicitudesDeDerivacion =session.createCriteria(SolicitudDerivacion.class)
                .add(Restrictions.eq("centroMedico",centroMedico))
                .add(Restrictions.eq("confirmado", false))
                .addOrder(Order.desc("fechaCreacion"))
                .list();
        return solicitudesDeDerivacion;
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDeDerivacionPorDerivacion(Derivacion derivacion) {
        final Session session= sessionFactory.getCurrentSession();
        List<SolicitudDerivacion> solicitudesDeDerivacion =session.createCriteria(SolicitudDerivacion.class)
                .add(Restrictions.eq("derivacion",derivacion))
                .addOrder(Order.asc("fechaCreacion"))
                .list();
        return solicitudesDeDerivacion;
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDerivacionAceptadasPorCentroMedicoYFecha(CentroMedico centroMedico, Date desde, Date hasta) {
        final Session session= sessionFactory.getCurrentSession();
        return session.createCriteria(SolicitudDerivacion.class)
                //.add(Restrictions.between("fechaCreacion", desde, hasta))
                .add(Restrictions.eq("centroMedico", centroMedico))
                .add(Restrictions.eq("aceptado", true)).list();
    }

    @Override
    public List<SolicitudDerivacion> obtenerSolicitudesDerivacionRechazadasPorCentroMedicoYFecha(CentroMedico centroMedico, Date desde, Date hasta) {
        final Session session= sessionFactory.getCurrentSession();
        return session.createCriteria(SolicitudDerivacion.class)
                //.add(Restrictions.between("fechaCreacion", desde, hasta))
                .add(Restrictions.eq("centroMedico", centroMedico))
                .add(Restrictions.eq("aceptado", false)).list();
    }
}
