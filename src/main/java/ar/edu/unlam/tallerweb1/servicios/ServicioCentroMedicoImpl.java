package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCentroMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioCentroMedico")
@Transactional
public class ServicioCentroMedicoImpl implements ServicioCentroMedico {
    private RepositorioCentroMedico repositorioCentroMedico;

    @Autowired
    public ServicioCentroMedicoImpl (RepositorioCentroMedico repositorioCentroMedico) { this.repositorioCentroMedico = repositorioCentroMedico;}

    @Override
    public List<CentroMedico> obtenerCentrosMedicos() {
        return repositorioCentroMedico.obtenerCentrosMedicos();
    }

    @Override
    public CentroMedico obtenerCentroMedicoPorId(Long id) throws Exception {
        if (repositorioCentroMedico.obtenerCentroMedicoPorId(id) != null) {
            return repositorioCentroMedico.obtenerCentroMedicoPorId(id);
        }
        throw new Exception("Hubo un error al buscar los datos");
    }
}
