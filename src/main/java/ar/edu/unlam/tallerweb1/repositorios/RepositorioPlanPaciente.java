package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.PlanPaciente;

import java.util.List;

public interface RepositorioPlanPaciente {
    List<PlanPaciente> obtenerPlanesPorPaciente(Long idPaciente);
}
