package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.HashSet;
import java.util.List;

public interface ServicioCentroMedico {
    List<CentroMedico> obtenerCentrosMedicos();
    HashSet<CentroMedico> obtenerCentrosMedicosPorPaciente(Paciente paciente);
    CentroMedico obtenerCentroMedicoPorId(Long id) throws Exception;
    HashSet<CentroMedico> centrosMedicosQuePoseenRequerimientosMedicos(HashSet<CentroMedico> centrosMedicos, RequerimientosMedicos requerimientosMedicos);
}
