package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    public List<Derivacion> derivacionesPorCobertura(Cobertura cobertura) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class)
                .add(Restrictions.eq("cobertura", cobertura))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.FINALIZADA))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.CANCELADA))
                .list();
    }

    @Override
    public List<Derivacion> derivacionesPorCoberturaFinalizadasYCanceladas(Cobertura cobertura) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class)
                .add(Restrictions.eq("cobertura", cobertura))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.ENBUSQUEDA))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.ENTRASLADO))
                .list();
    }

    public List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class)
                .add(Restrictions.eq("autor", autor))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.FINALIZADA))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.CANCELADA))
                .list();
    }

    @Override
    public List<Derivacion> filtrarDerivacionesFinalizadasYCanceladasPorFechaYUsuario(Date fechaMin, Date fechaMax, Usuario autor) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class)
                .add(Restrictions.eq("autor", autor))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.ENBUSQUEDA))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.ENTRASLADO))
                .add(Restrictions.between("fechaDerivacion", fechaMin, fechaMax))
                .addOrder(Order.asc("fechaDerivacion")).list();
    }

    @Override
    public List<Derivacion> filtrarDerivacionesPorCentroMedicoFinalizadasYCanceladas(CentroMedico centroMedico) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Derivacion.class)
                .add(Restrictions.eq("centroMedicoDeOrigen", centroMedico))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.ENBUSQUEDA))
                .add(Restrictions.ne("estadoDerivacion", EstadoDerivacion.ENTRASLADO))
                .list();
    }

    @Override
    public void eliminarDerivacion(Derivacion derivacion) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(derivacion);
    }

}