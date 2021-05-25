package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioPaciente")
@Transactional
public class ServicioPacienteImpl implements ServicioPaciente{

    private RepositorioPaciente servicioPacienteDao;

@Autowired
    public ServicioPacienteImpl(RepositorioPaciente servicioPacienteDao){this.servicioPacienteDao = servicioPacienteDao;}

    @Override
    public Paciente obtenerPacientePorDocumento(Integer documento) {
        return servicioPacienteDao.obtenerPacientePorNumeroDocumento(documento);
    }

    @Override
    public List<Paciente> obtenerPacientes() {
        return servicioPacienteDao.obtenerPacientes();
    }
}


