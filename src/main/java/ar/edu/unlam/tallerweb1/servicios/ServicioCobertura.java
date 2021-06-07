package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;

import java.util.List;

public interface ServicioCobertura {
    List<Cobertura> obtenerCoberturas();

    Cobertura obtenerCoberturaPorId(Long id_cobertura);
}
