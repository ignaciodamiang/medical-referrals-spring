package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;

import java.util.List;

public interface RepositorioPaciente {

    Paciente obtenerPacientePorNumeroDocumento(Integer documento);

    List<Paciente> obtenerPacientes();

    Paciente obtenerPacientePorId(Long idPaciente);
}
