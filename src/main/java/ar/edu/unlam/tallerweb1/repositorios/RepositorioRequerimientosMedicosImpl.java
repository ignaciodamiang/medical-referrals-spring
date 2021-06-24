package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.RequerimientosMedicos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioRequerimientosMedicos")
public class RepositorioRequerimientosMedicosImpl implements RepositorioRequerimientosMedicos{

    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioRequerimientosMedicosImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardaRequerimientosMedicos(RequerimientosMedicos requerimientosMedicos) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(requerimientosMedicos);
    }

    @Override
    public void modificarRequerimientosMedicos(RequerimientosMedicos requerimientosMedicos) {
        final Session session = sessionFactory.getCurrentSession();
        session.update(requerimientosMedicos);
    }
}
