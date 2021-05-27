package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;

import java.util.HashSet;

public interface ServicioPlan {
    Cobertura obtenerCoberturaPorPlan(Long id);
    HashSet<Cobertura> obetenerCoberturasPaciente(Long idPaciente);
}
