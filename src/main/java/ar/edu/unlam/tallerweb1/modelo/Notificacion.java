package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notificacion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String mensaje;
	private Date fecha;
	@ManyToOne
	private Traslado traslado;
	@ManyToOne
	private Derivacion derivacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Traslado getTraslado() {
		return traslado;
	}
	public void setTraslado(Traslado traslado) {
		this.traslado = traslado;
	}
	public Derivacion getDerivacion() {
		return derivacion;
	}
	public void setDerivacion(Derivacion derivacion) {
		this.derivacion = derivacion;
	}
	
}
