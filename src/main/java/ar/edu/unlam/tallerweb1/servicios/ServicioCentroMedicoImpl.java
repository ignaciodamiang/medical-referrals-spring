package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCentroMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioCentroMedico")
@Transactional
public class ServicioCentroMedicoImpl implements ServicioCentroMedico{
    RepositorioCentroMedico repositorioCentroMedicoDAO;

    @Autowired
    public ServicioCentroMedicoImpl( RepositorioCentroMedico repositorioCentroMedicoDAO){this.repositorioCentroMedicoDAO = repositorioCentroMedicoDAO;}


    @Override
    public CentroMedico obtenerCentroMedicoPorId(Integer id) {
        return repositorioCentroMedicoDAO.obtenerCentroMedicoPorId(id);
    }
}
