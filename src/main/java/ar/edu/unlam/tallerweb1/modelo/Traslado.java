package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Traslado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Derivacion derivacion;
    private String direccionOrigen;
    private String direccionDestino;

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
    public String getDireccionOrigen() {
        return direccionOrigen;
    }
    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }
    public String getDireccionDestino() {
        return direccionDestino;
    }
    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }
}
