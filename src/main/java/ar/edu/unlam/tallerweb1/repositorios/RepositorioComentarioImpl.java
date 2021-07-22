package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Comentario;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioComentario")
public class RepositorioComentarioImpl implements RepositorioComentario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioComentarioImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void guardarComentario(Comentario comentario) {
        final Session session =sessionFactory.getCurrentSession();
        session.save(comentario);
    }

    @Override
    public void modificarComentario(Comentario comentario) {
        final Session session =sessionFactory.getCurrentSession();
        session.update(comentario);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        final Session session =sessionFactory.getCurrentSession();
        session.remove(comentario);
    }

    @Override
    public List<Comentario> obtenerComentariosPorDerivacion(Derivacion derivacion) {
        final Session session =sessionFactory.getCurrentSession();
        return session.createCriteria(Comentario.class)
                .add(Restrictions.eq("derivacion", derivacion)).addOrder(Order.desc("fechaCreacion"))
                .list();
    }

    @Override
    public List<Comentario> obtenrComentariosPorSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        final Session session =sessionFactory.getCurrentSession();
        return session.createCriteria(Comentario.class)
                .add(Restrictions.eq("solicitudDerivacion", solicitudDerivacion)).addOrder(Order.desc("fechaCreacion"))
                .list();
    }
}
