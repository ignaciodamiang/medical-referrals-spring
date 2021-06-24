package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequerimientosMedicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean tomografo;
    private Boolean traumatologoDeguardia;
    private Boolean cirujanoDeGuardia;
    private Boolean cardiologoSeGuardia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTomografo() {
        return tomografo;
    }

    public void setTomografo(Boolean tomografo) {
        this.tomografo = tomografo;
    }

    public Boolean getTraumatologoDeguardia() {
        return traumatologoDeguardia;
    }

    public void setTraumatologoDeguardia(Boolean traumatologoDeguardia) {
        this.traumatologoDeguardia = traumatologoDeguardia;
    }

    public Boolean getCirujanoDeGuardia() {
        return cirujanoDeGuardia;
    }

    public void setCirujanoDeGuardia(Boolean cirujanoDeGuardia) {
        this.cirujanoDeGuardia = cirujanoDeGuardia;
    }

    public Boolean getCardiologoSeGuardia() {
        return cardiologoSeGuardia;
    }

    public void setCardiologoSeGuardia(Boolean cardiologoSeGuardia) {
        this.cardiologoSeGuardia = cardiologoSeGuardia;
    }
}
