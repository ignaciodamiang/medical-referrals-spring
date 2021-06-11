package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CancelacionTraslado;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCancelacionTraslado")
public class RepositorioCancelacionTrasladoImpl  implements RepositorioCancelacionTraslado{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCancelacionTrasladoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarCancelacionTraslado(CancelacionTraslado cancelacionTraslado) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(cancelacionTraslado);

    }

    @Override
    public CancelacionTraslado obtenerCancelaciónTrasladoPorTraslado(Traslado traslado) {
        final Session session = sessionFactory.getCurrentSession();
        return (CancelacionTraslado) session.createCriteria(CancelacionTraslado.class)
                .add(Restrictions.eq("traslado", traslado)).uniqueResult();
    }
}
