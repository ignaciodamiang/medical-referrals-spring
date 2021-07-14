package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comentario;

import javax.servlet.http.HttpServletRequest;

public interface ServicioComentario {
    void guardarComentario(Comentario comentario);
    void guardarComentarioDerivacion(Long idDerivacion,String mensaje ,HttpServletRequest request, String funcion) throws Exception;
}
