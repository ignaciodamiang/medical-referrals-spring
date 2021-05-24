package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioTraslado")
@Transactional
public class ServicioTrasladoImpl implements ServicioTraslado{

    RepositorioTraslado repositorioTraslado;

    @Autowired
    public ServicioTrasladoImpl(RepositorioTraslado repositorioTraslado){this.repositorioTraslado = repositorioTraslado;}


    @Override
    public void guardarTraslado(Traslado traslado) {
        repositorioTraslado.guardarTraslado(traslado);
    }

    @Override
    public void modificarTraslado(Traslado traslado) {
        repositorioTraslado.modificarTraslado(traslado);
    }
}
