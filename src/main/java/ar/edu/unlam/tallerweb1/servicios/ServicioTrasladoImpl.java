package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioTraslado")
@Transactional
public class ServicioTrasladoImpl implements ServicioTraslado{

    RepositorioTraslado repositorioTraslado;
    RepositorioDerivacion repositorioDerivacion;

    @Autowired
    public ServicioTrasladoImpl(RepositorioTraslado repositorioTraslado, RepositorioDerivacion repositorioDerivacion)
    {this.repositorioTraslado = repositorioTraslado;
     this.repositorioDerivacion=repositorioDerivacion;
    }


    @Override
    public void guardarTraslado(Traslado traslado) {
        repositorioTraslado.guardarTraslado(traslado);
    }

    @Override
    public void modificarTraslado(Traslado traslado) {
        repositorioTraslado.modificarTraslado(traslado);
    }

    @Override
    public List<Traslado> obtenerTrasladosPorCentroMedico(CentroMedico centroMedico) {
        return repositorioTraslado.obtenerTrasladosPorCentroMedico(centroMedico);
    }

    @Override
    public Traslado obtenerTrasladoPorDerivacion(Long idDerivacion) {
        Derivacion derivacion = repositorioDerivacion.verDerivacion(idDerivacion);
        return repositorioTraslado.obtenerTrasladoPorDerivacion(derivacion);
    }

    @Override
    public List<Traslado> obtenerTraslados() {
        return repositorioTraslado.obtenerTraslados();
    }

    @Override
    public Traslado obtenerTrasladoPorId(Long idTraslado) {
        return repositorioTraslado.obtenerTrasladoPorId(idTraslado);
    }
}
