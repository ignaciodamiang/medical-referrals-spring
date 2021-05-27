package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.PlanPaciente;

import java.util.HashSet;
import java.util.List;


public interface RepositorioPlanPaciente {
    List<PlanPaciente> obtenerPlanesPorPaciente(Paciente idPaciente);
}
