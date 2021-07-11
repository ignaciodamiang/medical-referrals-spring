package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.CentroMedicoDistancia;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Maps;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


public class ServicioCentroMedicoDistanciaImpl implements ServicioCentroMedicoDistancia{

    @Override
    public List<CentroMedicoDistancia> obtenerDistanciaCentroMedicoPaciente(HashSet<CentroMedico> centrosMedicos, Derivacion derivacion) throws URISyntaxException, IOException, InterruptedException {
        List<CentroMedicoDistancia> centroMedicoDistancias = new ArrayList<>();
        Maps maps = new Maps();
        Double distancia;
        for(CentroMedico centroMedico : centrosMedicos){
            distancia = maps.calcularDistancia(derivacion.getUbicacionPaciente(), centroMedico.getDireccion());
            centroMedicoDistancias.add(new CentroMedicoDistancia(centroMedico, distancia));
        }

        centroMedicoDistancias.sort(Comparator.comparing(CentroMedicoDistancia::getDistancia));

        return centroMedicoDistancias;
    }
}
