package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioDerivacion")
@Transactional
public class ServicioDerivacionImpl implements ServicioDerivacion{

    private RepositorioDerivacion respositorioDerivacion;

    @Autowired
    public ServicioDerivacionImpl (RepositorioDerivacion respositorioDerivacion) { this.respositorioDerivacion = respositorioDerivacion;}

    @Override
    public void guardarDerirvacion(Derivacion derivacion) {
        respositorioDerivacion.guardarDerivacion(derivacion);
    }

    @Override
    public void modificarDerivacion(Derivacion derivacion) {
        respositorioDerivacion.modificarDerivacion(derivacion);
    }
}
