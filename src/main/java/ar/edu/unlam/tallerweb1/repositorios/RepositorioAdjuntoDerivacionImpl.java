package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.AdjuntoDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioAdjuntoDerivacion")
public class RepositorioAdjuntoDerivacionImpl implements RepositorioAdjuntoDerivacion{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAdjuntoDerivacionImpl(SessionFactory sessionFactory){ this.sessionFactory=sessionFactory; }


    @Override
    public void guardarAdjunto(AdjuntoDerivacion adjuntoDerivacion) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(adjuntoDerivacion);
    }

    @Override
    public List<AdjuntoDerivacion> obtenerAdjuntosPorDerivacion(Derivacion derivacion) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(AdjuntoDerivacion.class)
                .add(Restrictions.eq("derivacion", derivacion)).list();
    }
}
