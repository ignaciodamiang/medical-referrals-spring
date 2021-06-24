package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.PlanCentroMedico;
import ar.edu.unlam.tallerweb1.modelo.PlanPaciente;

import java.util.HashSet;
import java.util.List;

public interface ServicioCentroMedico {
    List<CentroMedico> obtenerCentrosMedicos();
    HashSet<CentroMedico> obtenerCentrosMedicosPorPaciente(Paciente paciente);
    CentroMedico obtenerCentroMedicoPorId(Long id) throws Exception;
}
