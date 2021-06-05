package ar.edu.unlam.tallerweb1.derivacionTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDerivacionImpl;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class testDerivacion extends SpringTest {

    RepositorioDerivacionImpl repositorioDerivacion;

    @Test
    @Transactional @Rollback
    public void crearDerivacion(){
        repositorioDerivacion = new RepositorioDerivacionImpl(session().getSessionFactory());

        Paciente paciente = new Paciente();
        Cobertura cobertura = new Cobertura();
        Derivacion derivacion = new Derivacion();
        Usuario usuario = new Usuario();

        paciente.setNombreCompleto("Fabian");
        paciente.setDocumento(2020202);
        paciente.setFechaNacimiento(new Date());

        cobertura.setNombre("OSDE");

        usuario.setEmail("usuario@usuario.com");
        usuario.setPassword("usuario");
        usuario.setRol("Administrativo");

        session().save(paciente);
        session().save(cobertura);
        session().save(usuario);

        derivacion.setPaciente(paciente);
        derivacion.setUrgente(true);
        derivacion.setDiagnostico("covid");
        derivacion.setFechaDerivacion(new Date());
        derivacion.setParaQueSector("Terapia");
        derivacion.setCobertura(cobertura);
        derivacion.setAutor(usuario);

        session().save(derivacion);

        repositorioDerivacion.guardarDerivacion(derivacion);

        assertThat(derivacion.getId()).isNotNull();

    }

    @Test
    @Transactional @Rollback
    public void modificarDerivacion(){
        repositorioDerivacion = new RepositorioDerivacionImpl(session().getSessionFactory());

        Paciente paciente = new Paciente();
        Cobertura cobertura = new Cobertura();
        Derivacion derivacion = new Derivacion();
        Usuario usuario = new Usuario();

        paciente.setNombreCompleto("Fabian");
        paciente.setDocumento(2020202);
        paciente.setFechaNacimiento(new Date());

        cobertura.setNombre("OSDE");

        usuario.setEmail("usuario@usuario.com");
        usuario.setPassword("usuario");
        usuario.setRol("Administrativo");

        session().save(paciente);
        session().save(cobertura);
        session().save(usuario);

        derivacion.setPaciente(paciente);
        derivacion.setUrgente(true);
        derivacion.setDiagnostico("covid");
        derivacion.setFechaDerivacion(new Date());
        derivacion.setParaQueSector("Terapia");
        derivacion.setCobertura(cobertura);
        derivacion.setAutor(usuario);

        session().save(derivacion);

        derivacion.setParaQueSector("Guardia");

        repositorioDerivacion.guardarDerivacion(derivacion);

        assertThat(derivacion.getParaQueSector()).isEqualTo("Guardia");

    }
}
