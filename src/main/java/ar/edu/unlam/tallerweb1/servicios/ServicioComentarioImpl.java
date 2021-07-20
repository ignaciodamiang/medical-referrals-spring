package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comentario;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service("servicioComentario")
@Transactional
public class ServicioComentarioImpl implements ServicioComentario {

    private RepositorioComentario repositorioComentario;


    @Autowired
    public ServicioComentarioImpl(RepositorioComentario repositorioComentario){
        this.repositorioComentario = repositorioComentario;
    }

    @Override
    public void guardarComentario(Comentario comentario) {
        repositorioComentario.guardarComentario(comentario);
    }

    @Override
    public void guardarComentarioDerivacion(Derivacion derivacion, String mensaje ,Usuario usuario, String funcion){
        if(derivacion != null && usuario != null){
            Comentario comentario = new Comentario();
            comentario.setDerivacion(derivacion);
            comentario.setAutor(usuario);
            comentario.setFechaCreacion(new Date());

            switch (funcion.toUpperCase()){

                case "R": {
                    comentario.setMensaje(mensaje);
                    comentario.setAsunto("Registrar comentario");
                    this.guardarComentario(comentario);
                }

                case "G": {
                    comentario.setMensaje("Se ha generado la derivacion "+derivacion.getCodigo()+" para la cobertura "+derivacion.getCobertura().getNombre()+".");
                    comentario.setAsunto("Inicia");
                    this.guardarComentario(comentario);
                    break;
                }

                case "F": {
                    comentario.setMensaje("Ha finalizado la derivación "+derivacion.getCodigo());
                    comentario.setAsunto("Finalizado");
                    this.guardarComentario(comentario);
                    break;
                }

                case "C": {
                    comentario.setMensaje(mensaje);
                    comentario.setAsunto("Cancelado");
                    this.guardarComentario(comentario);
                    break;
                }

                default:{
                    break;
                }
            }

        }
    }

    @Override
    public void guardarComentarioSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion, String mensaje, Usuario usuario, String funcion) {

        if (solicitudDerivacion != null && usuario != null){
            Comentario comentario = new Comentario();
            comentario.setSolicitudDerivacion(solicitudDerivacion);
            comentario.setAutor(usuario);
            comentario.setFechaCreacion(new Date());


            switch (funcion.toUpperCase()){

                case "G": {
                    comentario.setMensaje("Se ha generado la solicitud "+solicitudDerivacion.getId()+" al centro médico "+solicitudDerivacion.getCentroMedico().getNombre());;
                    comentario.setAsunto("Inicia");
                    this.guardarComentario(comentario);
                    break;
                }

                case "R": {
                    comentario.setMensaje(mensaje);;
                    comentario.setAsunto("Rechazar");
                    this.guardarComentario(comentario);
                    break;
                }

                case "A": {
                    comentario.setMensaje(mensaje);
                    comentario.setAsunto("Aceptado");
                    this.guardarComentario(comentario);
                    break;
                }

                case "F": {
                    comentario.setMensaje("Ha finalizado la solicitud " +solicitudDerivacion.getId());
                    comentario.setAsunto("Finalizado");
                    this.guardarComentario(comentario);
                    break;
                }

                default:{
                    break;
                }
            }
        }
    }

    @Override
    public List<Comentario> obtenerComentariosPorDerivacion(Derivacion derivacion) {
        return repositorioComentario.obtenerComentariosPorDerivacion(derivacion);
    }


}
