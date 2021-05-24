package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Traslado;

import java.util.List;

public interface RepositorioTraslado {
    void guardarTraslado(Traslado traslado);
    void modificarTraslado(Traslado traslado);
    List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico);
}
