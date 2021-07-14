package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Derivacion derivacion;
    @ManyToOne
    private SolicitudDerivacion solicitudDerivacion;
    @ManyToOne
    private Traslado traslado;
    @ManyToOne
    private Usuario autor;
    private String asunto;
    private String mensaje;
    private Date fechaCreacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Derivacion getDerivacion() { return derivacion; }
    public void setDerivacion(Derivacion derivacion) { this.derivacion = derivacion; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public SolicitudDerivacion getSolicitudDerivacion() { return solicitudDerivacion; }
    public void setSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) { this.solicitudDerivacion = solicitudDerivacion; }
    public Traslado getTraslado() { return traslado; }
    public void setTraslado(Traslado traslado) { this.traslado = traslado; }
}

