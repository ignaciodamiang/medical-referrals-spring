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

	@Override
	public Cobertura obtenerCoberturaPorId(Long id) {
		return repositorioCobertura.obtenerCoberturaPorId(id);
	}
}
