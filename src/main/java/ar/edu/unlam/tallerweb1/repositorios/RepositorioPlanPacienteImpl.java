package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.PlanPaciente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository("repositorioPlanPaciente")
public class RepositorioPlanPacienteImpl implements RepositorioPlanPaciente{

    SessionFactory sessionFactory;

    @Autowired
    public RepositorioPlanPacienteImpl(SessionFactory sessionFactory){
       this.sessionFactory = sessionFactory;
    }


    @Override
    public List<PlanPaciente> obtenerPlanesPorPaciente(Paciente idPaciente) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(PlanPaciente.class)
                .add(Restrictions.eq("idPaciente.id", idPaciente.getId())).list();
    }
}
