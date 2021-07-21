package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Adjunto;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioAdjuntoDerivacion")
public class RepositorioAdjuntoImpl implements RepositorioAdjunto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAdjuntoImpl(SessionFactory sessionFactory){ this.sessionFactory=sessionFactory; }


    @Override
    public void guardarAdjunto(Adjunto adjuntoDerivacion) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(adjuntoDerivacion);
    }

    @Override
    public List<Adjunto> obtenerAdjuntosPorDerivacion(Derivacion derivacion) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Adjunto.class)
                .add(Restrictions.eq("derivacion", derivacion)).list();
    }

    @Override
    public List<Adjunto> obtenerAdjuntosPorSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Adjunto.class)
                .add(Restrictions.eq("solicitudDerivacion", solicitudDerivacion)).list();
    }


}
