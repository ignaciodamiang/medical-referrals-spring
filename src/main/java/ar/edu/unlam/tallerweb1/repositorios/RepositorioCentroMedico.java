package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.PlanCentroMedico;

import java.util.List;

public interface RepositorioCentroMedico {
    List<CentroMedico> obtenerCentrosMedicos();
    CentroMedico obtenerCentroMedicoPorId(Long id);
    List<PlanCentroMedico> obtenerPlanesCentrosMedicosPorPlan(Plan plan);
}
