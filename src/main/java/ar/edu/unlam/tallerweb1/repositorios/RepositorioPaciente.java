package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;

public interface RepositorioPaciente {

    Paciente obtenerPacientePorNumeroDocumento(Integer documento);
}
