package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;

import java.util.List;

public interface ServicioPlan {
    Cobertura obtenerCoberturaPorPlan(Long id);
    List<Cobertura> obetenerCoberturasPaciente(Long idPaciente);
}
