package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.CentroMedico;
import ar.edu.unlam.tallerweb1.modelo.CentroMedicoDistancia;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ServicioCentroMedicoDistanciaImpl implements ServicioCentroMedicoDistancia{

    @Override
    public List<CentroMedicoDistancia> obtenerDistanciaCentroMedicoPaciente(HashSet<CentroMedico> centrosMedicos, Derivacion derivacion) throws URISyntaxException, IOException, InterruptedException {
        List<CentroMedicoDistancia> centroMedicoDistancias = new ArrayList<>();
        String distancia ="";
        String json="";
        Maps maps = new Maps();
        CentroMedicoDistancia centroMedicoDistancia = new CentroMedicoDistancia();


        for(CentroMedico centroMedico : centrosMedicos){
            json = maps.calcularDistancia(derivacion.getUbicacionPaciente(),centroMedico.getDireccion());
            distancia = maps.devolverDistancia(json);
            centroMedicoDistancia.setCentroMedico(centroMedico);
            centroMedicoDistancia.setDistancia(distancia);
            centroMedicoDistancias.add(centroMedicoDistancia);
        }

        return centroMedicoDistancias;
    }
}
