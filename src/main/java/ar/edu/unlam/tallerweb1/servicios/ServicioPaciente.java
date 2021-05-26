package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;

import java.util.List;


public interface ServicioPaciente {
    Paciente obtenerPacientePorDocumento(Integer documento);
    List<Paciente> obtenerPacientes();
    Paciente obtenerPacientePorId(Long idPaciente);
}
