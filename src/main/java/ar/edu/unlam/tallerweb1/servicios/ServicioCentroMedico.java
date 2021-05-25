package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;

import java.util.List;

public interface ServicioCentroMedico {
    List<CentroMedico> obtenerCentrosMedicos();
    CentroMedico obtenerCentroMedicoPorId(Long id) throws Exception;
}
