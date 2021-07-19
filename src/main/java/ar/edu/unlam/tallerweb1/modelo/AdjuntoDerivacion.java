package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AdjuntoDerivacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Derivacion derivacion;
    private String imgPath;
    private Date fechaCreacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Derivacion getDerivacion() { return derivacion; }
    public void setDerivacion(Derivacion derivacion) { this.derivacion = derivacion; }
    public String getImgPath() { return imgPath; }
    public void setImgPath(String imgPath) { this.imgPath = imgPath; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
