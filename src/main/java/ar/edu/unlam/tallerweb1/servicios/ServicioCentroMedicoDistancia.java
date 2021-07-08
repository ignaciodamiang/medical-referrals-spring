package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.CentroMedicoDistancia;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;

public interface ServicioCentroMedicoDistancia {
    List<CentroMedicoDistancia> obtenerDistanciaCentroMedicoPaciente(HashSet<CentroMedico> centrosMedicos, Derivacion derivacion) throws URISyntaxException, IOException, InterruptedException;
}
