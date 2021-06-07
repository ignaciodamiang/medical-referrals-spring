package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("servicioDerivacion")
@Transactional
public class ServicioDerivacionImpl implements ServicioDerivacion{

    private RepositorioDerivacion respositorioDerivacion;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioDerivacionImpl (RepositorioDerivacion respositorioDerivacion, RepositorioUsuario repositorioUsuario) {
        this.respositorioDerivacion = respositorioDerivacion;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void guardarDerivacion(Derivacion derivacion, HttpServletRequest request) {
        Usuario autor = repositorioUsuario.obtenerUsuarioPorId((Long)request.getSession().getAttribute("ID_USUARIO"));
        derivacion.setAutor(autor);
        respositorioDerivacion.guardarDerivacion(derivacion);
    }

    @Override
    public void modificarDerivacion(Derivacion derivacion) {
        respositorioDerivacion.modificarDerivacion(derivacion);
    }

    @Override
    public List<Derivacion> listadoDerivaciones() {
        return respositorioDerivacion.listadoDerivaciones();
    }

    @Override
    public Derivacion verDerivacion(Long id) throws Exception {
        if(respositorioDerivacion.verDerivacion(id) != null){
            return respositorioDerivacion.verDerivacion(id);
        }
        throw new Exception("Hubo un error al buscar los datos");
    }

    @Override
    public void eliminarDerivacion(Derivacion derivacion) {
        respositorioDerivacion.eliminarDerivacion(derivacion);
    }

    @Override
    public List<Derivacion> obtenerDerivacionesPorAutor(Usuario autor) {
        return respositorioDerivacion.obtenerDerivacionesPorAutor(autor);
    }

    @Override
    public List<Derivacion> derivacionesPorCobertura(Cobertura cobertura) {
        return respositorioDerivacion.derivacionesPorCobertura(cobertura);
    }
}
