package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Traslado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    @OneToOne
    private Derivacion derivacion;
    @ManyToOne
    private CentroMedico centroMedico;
    @Enumerated(EnumType.STRING)
    private EstadoTraslado estadoTraslado;
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
    public CentroMedico getCentroMedico() { return centroMedico; }
    public void setCentroMedico(CentroMedico centroMedico) { this.centroMedico = centroMedico; }
    public EstadoTraslado getEstadoTraslado() {return estadoTraslado; }
    public void setEstadoTraslado(EstadoTraslado estadoTraslado) {this.estadoTraslado = estadoTraslado; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}
