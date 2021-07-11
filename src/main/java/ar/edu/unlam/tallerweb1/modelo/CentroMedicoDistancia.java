package ar.edu.unlam.tallerweb1.modelo;

public class CentroMedicoDistancia {

    private CentroMedico centroMedico;
    private Double distancia;

    public CentroMedicoDistancia(CentroMedico centroMedico, Double distancia) {
        this.centroMedico = centroMedico;
        this.distancia = distancia;
    }

    public CentroMedico getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(CentroMedico centroMedico) {
        this.centroMedico = centroMedico;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
}
