package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comentario;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioComentario {
    void guardarComentario(Comentario comentario);
    void guardarComentarioDerivacion(Derivacion derivacion, String mensaje , Usuario usuario, String funcion);
    void guardarComentarioSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion, String mensaje, Usuario usuario, String funcion);
}
