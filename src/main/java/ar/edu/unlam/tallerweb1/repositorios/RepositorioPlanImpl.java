package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Plan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioPlan")
public class RepositorioPlanImpl implements RepositorioPlan{
    SessionFactory sessionFactory;

    @Autowired
    public RepositorioPlanImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Plan obtenerPlanPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Plan.class, id);
    }
}
