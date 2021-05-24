package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class SolicitudDerivacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Derivacion derivacion;
    @ManyToOne
    private CentroMedico centroMedico;
    private Boolean aceptado;
    private Boolean confirmado;

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
}
