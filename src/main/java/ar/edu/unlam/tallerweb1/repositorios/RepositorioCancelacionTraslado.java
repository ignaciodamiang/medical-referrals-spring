package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.CancelacionTraslado;
import ar.edu.unlam.tallerweb1.modelo.Traslado;

public interface RepositorioCancelacionTraslado {
    void guardarCancelacionTraslado(CancelacionTraslado cancelacionTraslado);
    CancelacionTraslado obtenerCancelaciónTrasladoPorTraslado(Traslado traslado);
}
