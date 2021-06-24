package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Derivacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EstadoDerivacion estadoDerivacion;
    @ManyToOne
    private Paciente paciente;
    private Boolean urgente;
    private String diagnostico;
    private Date fechaDerivacion;
    private String paraQueSector;
    @ManyToOne
    private Cobertura cobertura;
    @OneToOne
    private Usuario autor;
    @OneToOne
    RequerimientosMedicos requerimientosMedicos;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Boolean getUrgente() {
        return urgente;
    }
    public void setUrgente(Boolean urgente) {
        this.urgente = urgente;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public Date getFechaDerivacion() {
        return fechaDerivacion;
    }
    public void setFechaDerivacion(Date fechaDerivacion) {
        this.fechaDerivacion = fechaDerivacion;
    }
    public String getParaQueSector() {
        return paraQueSector;
    }
    public void setParaQueSector(String paraQueSector) {
        this.paraQueSector = paraQueSector;
    }
    public Cobertura getCobertura() {
        return cobertura;
    }
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor;}
    
	public EstadoDerivacion getEstadoDerivacion() {
		return estadoDerivacion;
	}
	public void setEstadoDerivacion(EstadoDerivacion estadoDerivacion) {
		this.estadoDerivacion = estadoDerivacion;
	}

    public RequerimientosMedicos getRequerimientosMedicos() {
        return requerimientosMedicos;
    }

    public void setRequerimientosMedicos(RequerimientosMedicos requerimientosMedicos) {
        this.requerimientosMedicos = requerimientosMedicos;
    }
}
