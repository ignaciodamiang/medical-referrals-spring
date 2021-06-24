package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class CentroMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private Boolean guardia;
    private Boolean salaComun;
    private Boolean terapia;
    @OneToOne
    RequerimientosMedicos requerimientosMedicos;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Boolean getGuardia() {
        return guardia;
    }
    public void setGuardia(Boolean guardia) {
        this.guardia = guardia;
    }
    public Boolean getSalaComun() {
        return salaComun;
    }
    public void setSalaComun(Boolean salaComun) {
        this.salaComun = salaComun;
    }
    public Boolean getTerapia() {
        return terapia;
    }
    public void setTerapia(Boolean terapia) {
        this.terapia = terapia;
    }

    public RequerimientosMedicos getRequerimientosMedicos() {
        return requerimientosMedicos;
    }

    public void setRequerimientosMedicos(RequerimientosMedicos requerimientosMedicos) {
        this.requerimientosMedicos = requerimientosMedicos;
    }
}
