package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;

import java.util.Date;
import java.util.List;

public interface RepositorioTraslado {
    void guardarTraslado(Traslado traslado);
    void modificarTraslado(Traslado traslado);
    List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico);
    List<Traslado> obtenerTrasladosPorCentroMedicoCanceladosPorFecha(CentroMedico centroMedico, Date desde, Date hasta);
    List<Traslado> obtenerTrasladosPorCentroMedicoFinalizadosPorFecha(CentroMedico centroMedico, Date desde, Date hasta);
    Traslado obtenerTrasladoPorDerivacion(Derivacion derivacion);
    List<Traslado> obtenerTraslados();
    Traslado obtenerTrasladoPorId(Long idTraslado);
}
