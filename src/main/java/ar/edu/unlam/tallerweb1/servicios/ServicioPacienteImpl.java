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

    private RepositorioPaciente repositorioPacienteDao;

@Autowired
    public ServicioPacienteImpl(RepositorioPaciente servicioPacienteDao){this.repositorioPacienteDao = servicioPacienteDao;}

    @Override
    public Paciente obtenerPacientePorDocumento(Integer documento) {
        return repositorioPacienteDao.obtenerPacientePorNumeroDocumento(documento);
    }

    @Override
    public List<Paciente> obtenerPacientes() {
        return repositorioPacienteDao.obtenerPacientes();
    }

    @Override
    public Paciente obtenerPacientePorId(Long idPaciente) {
        return repositorioPacienteDao.obtenerPacientePorId(idPaciente);
    }
}


