package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCobertura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioCobertura")
@Transactional
public class ServicioCoberturaImpl implements ServicioCobertura{

    private RepositorioCobertura repositorioCobertura;

    @Autowired
    public ServicioCoberturaImpl (RepositorioCobertura repositorioCobertura){this.repositorioCobertura = repositorioCobertura;}

    @Override
    public List<Cobertura> obtenerCoberturas() {
        return repositorioCobertura.obtenerCoberturas();
    }

<<<<<<< HEAD
    @Override
    public Cobertura obtenerCoberturaPorId(Long id) {
        return repositorioCobertura.obtenerCoberturaPorId(id);
    }
=======
	@Override
	public Cobertura obtenerCoberturaPorId(Long id) {
		return repositorioCobertura.obtenerCoberturaPorId(id);
	}
>>>>>>> 9f70280b77834244d6c26641a6552add2eb35383
}
