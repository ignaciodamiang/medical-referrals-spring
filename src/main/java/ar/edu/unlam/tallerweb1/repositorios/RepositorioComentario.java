package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Comentario;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;

import java.util.List;

public interface RepositorioComentario {
    void guardarComentario(Comentario comentario);
    void modificarComentario(Comentario comentario);
    void eliminarComentario(Comentario comentario);
    List<Comentario> obtenerComentariosPorDerivacion(Derivacion derivacion);
    List<Comentario> obtenrComentariosPorSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
}
