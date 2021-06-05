package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;

import java.util.List;

public interface RepositorioCobertura {
    List<Cobertura> obtenerCoberturas();
    Cobertura obtenerCoberturaPorId(Long id);
}
