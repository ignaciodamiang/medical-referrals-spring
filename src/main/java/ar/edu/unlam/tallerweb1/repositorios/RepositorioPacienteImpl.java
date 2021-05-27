package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioPaciente")
public class RepositorioPacienteImpl implements RepositorioPaciente{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPacienteImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Paciente obtenerPacientePorNumeroDocumento(Integer documento) {
        final Session session = sessionFactory.getCurrentSession();
        return (Paciente) session.createCriteria(Paciente.class)
                .add(Restrictions.eq("documento",documento))
                .uniqueResult();
    }

    @Override
    public List<Paciente> obtenerPacientes() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Paciente.class).list();
    }

    @Override
    public Paciente obtenerPacientePorId(Long idPaciente) {
        final Session session = sessionFactory.getCurrentSession();
        return (Paciente) session.createCriteria(Paciente.class)
                .add(Restrictions.eq("id", idPaciente)).uniqueResult();
    }
}
