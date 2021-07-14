package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTraslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

@Service("servicioTraslado")
@Transactional
public class ServicioTrasladoImpl implements ServicioTraslado{

    private RepositorioTraslado repositorioTraslado;
    private RepositorioDerivacion repositorioDerivacion;
    private ServicioDerivacion servicioDerivacion;
    private ServicioNotificacion servicioNotificacion;
    private ServicioMail servicioMail;
    private ServicioSolicitudDerivacion servicioSolicitudDerivacion;

    @Autowired
    public ServicioTrasladoImpl(RepositorioTraslado repositorioTraslado, RepositorioDerivacion repositorioDerivacion,
                                ServicioDerivacion servicioDerivacion,ServicioNotificacion servicioNotificacion,
                                ServicioMail servicioMail, ServicioSolicitudDerivacion servicioSolicitudDerivacion)
    {this.repositorioTraslado = repositorioTraslado;
     this.repositorioDerivacion=repositorioDerivacion;
     this.servicioDerivacion=servicioDerivacion;
     this.servicioNotificacion=servicioNotificacion;
     this.servicioMail=servicioMail;
     this.servicioSolicitudDerivacion = servicioSolicitudDerivacion;
    }


    @Override
    public void guardarTraslado(Long idSolicitud) {
        SolicitudDerivacion solicitudDerivacion = servicioSolicitudDerivacion.obtenerSolicitudDerivacionPorId(idSolicitud);
        Traslado traslado = new Traslado();
        traslado.setCentroMedico(solicitudDerivacion.getCentroMedico());
        traslado.setDerivacion(solicitudDerivacion.getDerivacion());
        traslado.setEstadoTraslado(EstadoTraslado.ENCURSO);
        solicitudDerivacion.setConfirmado(true);
        Derivacion derivacion = solicitudDerivacion.getDerivacion();
        derivacion.setEstadoDerivacion(EstadoDerivacion.ENTRASLADO);
        servicioDerivacion.modificarDerivacion(derivacion);
        servicioSolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
        repositorioTraslado.guardarTraslado(traslado);
        servicioNotificacion.guardarNotificacion(traslado, "G", "");
    }

    @Override
    public void modificarTraslado(Traslado traslado) {
        repositorioTraslado.modificarTraslado(traslado);
    }

    @Override
    public void finalizarTraslado(Long idTraslado) throws MessagingException {
        Traslado traslado = this.obtenerTrasladoPorId(idTraslado);
        traslado.setEstadoTraslado(EstadoTraslado.FINALIZADO);
        Derivacion derivacion = traslado.getDerivacion();
        derivacion.setEstadoDerivacion(EstadoDerivacion.FINALIZADA);
        servicioDerivacion.modificarDerivacion(derivacion);
        this.modificarTraslado(traslado);
        servicioNotificacion.guardarNotificacion(traslado, "F","");
        servicioMail.enviarMsj(derivacion.getAutor().getEmail(),"Ha finalizado un translado",
                "El traslado del paciente:  '"+derivacion.getPaciente().getNombreCompleto()+
                        "', destino: '"+ traslado.getCentroMedico().getNombre()+" ("+traslado.getCentroMedico().getDireccion()+")"+
                        " ha finalizado correctamente. <br> <a href='http://localhost:8080/proyecto_derivaciones_war_exploded/listado-derivacion'>ver derivaciones</a> <br>");
    }

    @Override
    public void cancelarTraslado(Long idTraslado, String mensaje) throws MessagingException {
        Traslado traslado = this.obtenerTrasladoPorId(idTraslado);
        traslado.setEstadoTraslado(EstadoTraslado.CANCELADO);
        Derivacion derivacion = traslado.getDerivacion();
        derivacion.setEstadoDerivacion(EstadoDerivacion.ENBUSQUEDA);
        servicioDerivacion.modificarDerivacion(derivacion);
        this.modificarTraslado(traslado);
        servicioNotificacion.guardarNotificacion(traslado, "C",mensaje);
        servicioMail.enviarMsj(derivacion.getAutor().getEmail(),"Se ha cancelado un translado",
                "El traslado del paciente:  '"+derivacion.getPaciente().getNombreCompleto()+
                        "', destino: '"+
                        traslado.getCentroMedico().getNombre()+" ("+traslado.getCentroMedico().getDireccion()+")"+
                        ", MOTIVO: "+mensaje+" "+"<br> <a href='http://localhost:8080/proyecto_derivaciones_war_exploded/listado-derivacion'>ver derivaciones</a>");

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
