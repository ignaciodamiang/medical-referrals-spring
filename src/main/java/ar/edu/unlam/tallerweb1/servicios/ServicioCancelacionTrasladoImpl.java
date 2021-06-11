package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CancelacionTraslado;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCancelacionTraslado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("servicioCancelacionTraslado")
@Transactional
public class ServicioCancelacionTrasladoImpl implements ServicioCancelacionTraslado{

    private RepositorioCancelacionTraslado repositorioCancelacionTraslado;
    private RepositorioTraslado repositorioTraslado;

    @Autowired
    public ServicioCancelacionTrasladoImpl(RepositorioCancelacionTraslado repositorioCancelacionTraslado, RepositorioTraslado repositorioTraslado){
        this.repositorioCancelacionTraslado = repositorioCancelacionTraslado;
        this.repositorioTraslado = repositorioTraslado;
    }


    @Override
    public void crearCancelacionTraslado(Long idTraslado, String mensaje) {
        Traslado traslado = repositorioTraslado.obtenerTrasladoPorId(idTraslado);
        CancelacionTraslado cancelacionTraslado = new CancelacionTraslado();
        cancelacionTraslado.setTraslado(traslado);
        cancelacionTraslado.setMensaje(mensaje);
        cancelacionTraslado.setFecha(new Date());
        repositorioCancelacionTraslado.guardarCancelacionTraslado(cancelacionTraslado);
    }

    @Override
    public CancelacionTraslado obtenerCancelaciónTraslado(Long idTraslado) {
        Traslado traslado = repositorioTraslado.obtenerTrasladoPorId(idTraslado);
        return repositorioCancelacionTraslado.obtenerCancelaciónTrasladoPorTraslado(traslado);
    }
}
