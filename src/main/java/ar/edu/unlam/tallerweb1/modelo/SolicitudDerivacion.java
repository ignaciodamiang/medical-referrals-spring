package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SolicitudDerivacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String descripcion;
    @ManyToOne
    private Derivacion derivacion;
    @ManyToOne
    private CentroMedico centroMedico;
    private Boolean aceptado;
    private Boolean confirmado;
    private Date fechaCreacion;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Derivacion getDerivacion() {
        return derivacion;
    }
    public void setDerivacion(Derivacion derivacion) {
        this.derivacion = derivacion;
    }
    public CentroMedico getCentroMedico() {
        return centroMedico;
    }
    public void setCentroMedico(CentroMedico centroMedico) {
        this.centroMedico = centroMedico;
    }
    public Boolean getAceptado() {
        return aceptado;
    }
    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }
    public Boolean getConfirmado() {
        return confirmado;
    }
    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }
    public Date getFechaCreacion() {return fechaCreacion;}
    public void setFechaCreacion(Date fechaCreacion) {this.fechaCreacion = fechaCreacion;}
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
