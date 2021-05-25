package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCentroMedico")
public class RepositorioCentroMedicoImpl implements RepositorioCentroMedico{

    SessionFactory sessionFactory;

    @Autowired
    public RepositorioCentroMedicoImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}


    @Override
    public CentroMedico obtenerCentroMedicoPorId(Integer id) {
        final Session session =sessionFactory.getCurrentSession();
        return session.get(CentroMedico.class, id);
    }
}
