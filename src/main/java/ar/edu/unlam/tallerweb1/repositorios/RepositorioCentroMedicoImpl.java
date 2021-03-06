package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.PlanCentroMedico;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCentroMedicoImpl implements RepositorioCentroMedico {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCentroMedicoImpl(SessionFactory sessionFactory){this.sessionFactory=sessionFactory;}

    @Override
    public List<CentroMedico> obtenerCentrosMedicos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CentroMedico.class).list();
    }

    @Override
    public CentroMedico obtenerCentroMedicoPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        CentroMedico centroMedico = session.get(CentroMedico.class,id);
        return  centroMedico;
    }

    @Override
    public List<PlanCentroMedico> obtenerPlanesCentrosMedicosPorPlan(Plan plan) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(PlanCentroMedico.class)
                .add(Restrictions.eq("idPlan.id", plan.getId()))
                .list();
    }
}
