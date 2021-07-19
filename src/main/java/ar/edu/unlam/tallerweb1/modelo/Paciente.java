package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCompleto;
    private Integer documento;
    private Date fechaNacimiento;
    private String foto;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public int getDocumento() {
        return documento;
    }
    public void setDocumento(Integer dni) {
        this.documento = dni;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}
