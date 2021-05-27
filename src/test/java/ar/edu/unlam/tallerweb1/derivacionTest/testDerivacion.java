package ar.edu.unlam.tallerweb1.derivacionTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
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

        paciente.setNombreCompleto("Fabian");
        paciente.setDocumento(2020202);
        paciente.setFechaNacimiento(new Date());

        cobertura.setNombre("OSDE");

        session().save(paciente);
        session().save(cobertura);

        derivacion.setFinalizada(false);
        derivacion.setPaciente(paciente);
        derivacion.setUrgente(true);
        derivacion.setDiagnostico("covid");
        derivacion.setFechaDerivacion(new Date());
        derivacion.setParaQueSector("Terapia");
        derivacion.setCobertura(cobertura);

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

        paciente.setNombreCompleto("Fabian");
        paciente.setDocumento(2020202);
        paciente.setFechaNacimiento(new Date());

        cobertura.setNombre("OSDE");

        session().save(paciente);
        session().save(cobertura);

        derivacion.setFinalizada(false);
        derivacion.setPaciente(paciente);
        derivacion.setUrgente(true);
        derivacion.setDiagnostico("covid");
        derivacion.setFechaDerivacion(new Date());
        derivacion.setParaQueSector("Terapia");
        derivacion.setCobertura(cobertura);

        session().save(derivacion);

        derivacion.setParaQueSector("nose");

        repositorioDerivacion.guardarDerivacion(derivacion);

        assertThat(derivacion.getParaQueSector()).isEqualTo("nose");

    }
}
