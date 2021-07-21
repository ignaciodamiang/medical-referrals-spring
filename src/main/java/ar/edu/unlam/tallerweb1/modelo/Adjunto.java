package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Adjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Derivacion derivacion;
    @ManyToOne
    private SolicitudDerivacion solicitudDerivacion;
    private String imgPath;
    private Date fechaCreacion;
    private String titulo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Derivacion getDerivacion() { return derivacion; }
    public void setDerivacion(Derivacion derivacion) { this.derivacion = derivacion; }
    public String getImgPath() { return imgPath; }
    public void setImgPath(String imgPath) { this.imgPath = imgPath; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public SolicitudDerivacion getSolicitudDerivacion() { return solicitudDerivacion; }
    public void setSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) { this.solicitudDerivacion = solicitudDerivacion; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
}
