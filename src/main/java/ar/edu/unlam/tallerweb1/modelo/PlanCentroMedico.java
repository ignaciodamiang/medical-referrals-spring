package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class PlanCentroMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CentroMedico idCentroMedico;
    @ManyToOne
    private Plan idPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CentroMedico getIdCentroMedico() {
        return idCentroMedico;
    }

    public void setIdCentroMedico(CentroMedico idCentroMedico) {
        this.idCentroMedico = idCentroMedico;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }
}
