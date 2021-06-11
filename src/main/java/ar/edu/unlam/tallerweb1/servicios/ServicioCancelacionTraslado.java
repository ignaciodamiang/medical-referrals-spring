package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CancelacionTraslado;

public interface ServicioCancelacionTraslado {
    void crearCancelacionTraslado(Long idTraslado, String mensaje);
    CancelacionTraslado obtenerCancelaciónTraslado(Long idTraslado);
}
