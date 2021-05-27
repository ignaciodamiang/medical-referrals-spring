package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Traslado.class)
        .add(Restrictions.eq("centroMedico",centroMedico)).list();
    }

    @Override
    public List<Traslado> obtenerTraslados() {
        final Session session= sessionFactory.getCurrentSession();
        List<Traslado> traslados = session.createCriteria(Traslado.class)
                .addOrder(Order.desc("id")).list();
        return traslados;
    }
}
