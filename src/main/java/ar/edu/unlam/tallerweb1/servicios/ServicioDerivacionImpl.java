package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("servicioDerivacion")
@Transactional
public class ServicioDerivacionImpl implements ServicioDerivacion{

    private RepositorioDerivacion respositorioDerivacion;
    private RepositorioUsuario repositorioUsuario;
    private ServicioPaciente servicioPaciente;
    private ServicioRequerimientosMedicos servicioRequerimientosMedicos;

    @Autowired
    public ServicioDerivacionImpl (RepositorioDerivacion respositorioDerivacion, RepositorioUsuario repositorioUsuario, ServicioPaciente servicioPaciente,
                                   ServicioRequerimientosMedicos servicioRequerimientosMedicos) {
        this.respositorioDerivacion = respositorioDerivacion;
        this.repositorioUsuario = repositorioUsuario;
        this.servicioPaciente = servicioPaciente;
        this.servicioRequerimientosMedicos = servicioRequerimientosMedicos;
    }

    @Override
    public void guardarDerivacion(Derivacion derivacion,HttpServletRequest request, Long idPaciente, RequerimientosMedicos requerimientosMedicos, Boolean urgente, String ubicacionPaciente) {
        Paciente paciente = servicioPaciente.obtenerPacientePorId(idPaciente);
        servicioRequerimientosMedicos.guardaRequerimientosMedicos(requerimientosMedicos);
        if(paciente != null && requerimientosMedicos.getId() != 0) {
            Usuario autor = repositorioUsuario.obtenerUsuarioPorId((Long) request.getSession().getAttribute("ID_USUARIO"));

            derivacion.setAutor(autor);
            derivacion.setFechaDerivacion(new Date());
            derivacion.setPaciente(paciente);
            derivacion.setEstadoDerivacion(EstadoDerivacion.ENBUSQUEDA);
            derivacion.setRequerimientosMedicos(requerimientosMedicos);
            derivacion.setUrgente(urgente);
            derivacion.setUbicacionPaciente(ubicacionPaciente);
            respositorioDerivacion.guardarDerivacion(derivacion);
        }
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

    @Override
    public List<Derivacion> filtrarDerivacionesPorFecha(Long idUsuario, String fechaMin, String fechaMax) throws ParseException {
        Usuario usuario = repositorioUsuario.obtenerUsuarioPorId(idUsuario);

        Date desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMin);
        Date hasta = new SimpleDateFormat("yyyy-MM-dd").parse(fechaMax);
        Date fix = new Date(hasta.getTime()+(1000 * 60 * 60 * 24));
        return respositorioDerivacion.filtrarDerivacionesPorFecha(desde, fix, usuario);
    }
}
