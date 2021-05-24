package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;



public interface ServicioPaciente {
    Paciente obtenerPacientePorDocumento(Integer documento);
}
