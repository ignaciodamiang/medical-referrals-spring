package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;

import java.util.List;

public interface RepositorioCentroMedico {
    List<CentroMedico> obtenerCentrosMedicos();
    CentroMedico obtenerCentroMedicoPorId(Long id);
}
