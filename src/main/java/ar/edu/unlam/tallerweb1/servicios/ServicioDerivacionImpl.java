package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("servicioDerivacion")
@Transactional
public class ServicioDerivacionImpl implements ServicioDerivacion{

    private RepositorioDerivacion respositorioDerivacion;
    private ServicioUsuario repositorioUsuario;
    private ServicioPaciente servicioPaciente;
    private ServicioRequerimientosMedicos servicioRequerimientosMedicos;
    private ServicioNotificacion servicioNotificacion;
    private ServicioDerivador servicioDerivador;
    private ServicioMail servicioMail;
    private ServicioComentario servicioComentario;
    private ServicioCobertura servicioCobertura;

    @Autowired
    public ServicioDerivacionImpl (RepositorioDerivacion respositorioDerivacion, ServicioUsuario repositorioUsuario, ServicioPaciente servicioPaciente,
                                   ServicioRequerimientosMedicos servicioRequerimientosMedicos, ServicioNotificacion servicioNotificacion,
                                   ServicioDerivador servicioDerivador, ServicioMail servicioMail, ServicioComentario servicioComentario, ServicioCobertura servicioCobertura) {
        this.respositorioDerivacion = respositorioDerivacion;
        this.repositorioUsuario = repositorioUsuario;
        this.servicioPaciente = servicioPaciente;
        this.servicioRequerimientosMedicos = servicioRequerimientosMedicos;
        this.servicioNotificacion = servicioNotificacion;
        this.servicioDerivador = servicioDerivador;
        this.servicioMail = servicioMail;
        this.servicioComentario = servicioComentario;
        this.servicioCobertura = servicioCobertura;
    }

    @Override
    public void guardarDerivacion(Derivacion derivacion,HttpServletRequest request, Long idPaciente, RequerimientosMedicos requerimientosMedicos, Boolean urgente, String ubicacionPaciente) throws Exception {
        Paciente paciente = servicioPaciente.obtenerPacientePorId(idPaciente);
        servicioRequerimientosMedicos.guardaRequerimientosMedicos(requerimientosMedicos);
        if(paciente != null && requerimientosMedicos.getId() != 0) {
            Usuario autor = repositorioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));

            derivacion.setAutor(autor);
            derivacion.setFechaDerivacion(new Date());
            derivacion.setPaciente(paciente);
            derivacion.setEstadoDerivacion(EstadoDerivacion.ENBUSQUEDA);
            derivacion.setRequerimientosMedicos(requerimientosMedicos);
            derivacion.setUrgente(urgente);
            derivacion.setUbicacionPaciente(ubicacionPaciente);
            respositorioDerivacion.guardarDerivacion(derivacion);
            if(derivacion.getUrgente()) {
                servicioNotificacion.guardarNotificacion(derivacion, "U","");
                /*  se mande un mail a todos los derivadores de esa cobertura cuando se genera una derivacion  */
                /* testear correctamente con correos reales */
                for (Derivador derivador : servicioDerivador.obtenerDerivadoresPorCobertura(derivacion.getCobertura())){
                    servicioMail.enviarMsj(derivador.getUsuario().getEmail(),"Se ha generado una derivacion.","se ha generado una derivacion para paciente: "+derivacion.getPaciente().getNombreCompleto());
                }
            }
            servicioComentario.guardarComentarioDerivacion(derivacion, "",autor, "G");
        }
    }

    @Override
    public void modificarDerivacion(Derivacion derivacion) {
        respositorioDerivacion.modificarDerivacion(derivacion);
    }

    @Override
    public void finalizarDerivacion(Derivacion derivacion, HttpServletRequest request) {
        Usuario usuario = repositorioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        String estadoAnterior = derivacion.getEstadoDerivacion().toString();
        derivacion.setEstadoDerivacion(EstadoDerivacion.FINALIZADA);
        this.modificarDerivacion(derivacion);
        servicioComentario.guardarComentarioDerivacion(derivacion,"",derivacion.getAutor(),"F");
        Comentario comentario = new Comentario();
        comentario.setAsunto("Cambio de estado");
        comentario.setDerivacion(derivacion);
        comentario.setAutor(usuario);
        comentario.setFechaCreacion(new Date());
        comentario.setMensaje("cambio de estado "+estadoAnterior+ " a "+derivacion.getEstadoDerivacion().toString());
        servicioComentario.guardarComentario(comentario);
    }

    @Override
    public void cancelarDerivacion(Long idDerivacion, String mensaje, HttpServletRequest request) throws Exception {
        Usuario usuario = repositorioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        Derivacion derivacion = this.verDerivacion(idDerivacion);
        String estadoAnterior = derivacion.getEstadoDerivacion().toString();
        derivacion.setEstadoDerivacion(EstadoDerivacion.CANCELADA);
        this.modificarDerivacion(derivacion);
        servicioNotificacion.guardarNotificacion(derivacion, "C", mensaje);
        servicioComentario.guardarComentarioDerivacion(derivacion, mensaje, derivacion.getAutor(),"C");
        Comentario comentario = new Comentario();
        comentario.setAsunto("Cambio de estado");
        comentario.setDerivacion(derivacion);
        comentario.setAutor(usuario);
        comentario.setFechaCreacion(new Date());
        comentario.setMensaje("cambio de estado "+estadoAnterior+ " a "+derivacion.getEstadoDerivacion().toString());
        servicioComentario.guardarComentario(comentario);
    }

    @Override
    public void derivacionEnTraslado(Derivacion derivacion, HttpServletRequest request) {
        Usuario usuario = repositorioUsuario.consultarUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));
        String estadoAnterior = derivacion.getEstadoDerivacion().toString();
        derivacion.setEstadoDerivacion(EstadoDerivacion.ENTRASLADO);
        this.modificarDerivacion(derivacion);
        Comentario comentario = new Comentario();
        comentario.setAsunto("Cambio de estado");
        comentario.setDerivacion(derivacion);
        comentario.setAutor(usuario);
        comentario.setFechaCreacion(new Date());
        comentario.setMensaje("cambio de estado "+estadoAnterior+ " a "+derivacion.getEstadoDerivacion().toString());
        servicioComentario.guardarComentario(comentario);
    }

    @Override
    public List<Derivacion> listadoDerivaciones() {
        return respositorioDerivacion.listadoDerivaciones();
    }

    @Override
    public Derivacion verDerivacion(Long id) throws Exception {
        if(respositorioDerivacion.verDerivacion(id) != null){
            return respositorioDerivacion.verDerivacion(id);
        }
        throw new Exception("Hubo un error al buscar los datos");
    }

    @Override
    public void eliminarDerivacion(Derivacion derivacion) {
        respositorioDerivacion.eliminarDerivacion(derivacion);
    }

    @Override
    public List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor) {
        return respositorioDerivacion.obtenerDerivacionesPorAutor(autor);
    }

    @Override
    public List<Derivacion> derivacionesPorCobertura(Cobertura cobertura) {
        return respositorioDerivacion.derivacionesPorCobertura(cobertura);
    }

    @Override
    public List<Derivacion> derivacionesPorCoberturaFinalizadasYCanceladas(HttpServletRequest request){
        Long idCobertura = (Long) request.getSession().getAttribute("ID_COBERTURA");
        Cobertura cobertura = servicioCobertura.obtenerCoberturaPorId(idCobertura);
        if (cobertura != null){
            return respositorioDerivacion.derivacionesPorCoberturaFinalizadasYCanceladas(cobertura);
        }
        return null;
    }

    @Override
    public List<Derivacion> filtrarDerivacionesPorFecha(Long idUsuario, String fechaMin, String fechaMax) throws ParseException {
        Usuario usuario = repositorioUsuario.consultarUsuarioPorId(idUsuario);

        Date desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMin);
        Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMax);
        Date fix = new Date(hasta.getTime()+(1000 * 60 * 60 * 24));
        return respositorioDerivacion.filtrarDerivacionesPorFecha(desde, fix, usuario);
    }
}
