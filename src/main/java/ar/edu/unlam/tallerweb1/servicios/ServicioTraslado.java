package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioTraslado {
    void guardarTraslado(Traslado traslado);
    void modificarTraslado(Traslado traslado);
    List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico);
    Traslado obtenerTrasladoPorDerivacion(Long idDerivacion);
    List<Traslado> obtenerTraslados();
}
