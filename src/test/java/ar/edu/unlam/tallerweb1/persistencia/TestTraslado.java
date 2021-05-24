package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTrasladoImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTraslado extends SpringTest {

    RepositorioTrasladoImpl repositorioTraslado;

    @Test
    @Transactional @Rollback
    public void crearTraslado(){

        repositorioTraslado = new RepositorioTrasladoImpl(session().getSessionFactory());

        Paciente paciente = new Paciente();
        Cobertura cobertura = new Cobertura();
        Derivacion derivacion = new Derivacion();
        Traslado traslado = new Traslado();

        paciente.setDocumento(39830167);
        paciente.setFechaNacimiento(new Date());
        paciente.setNombreCompleto("Matias Guerrero");

        cobertura.setNombre("OSDE");

        session().save(paciente);
        session().save(cobertura);

        derivacion.setFechaDerivacion(new Date());
        derivacion.setCobertura(cobertura);
        derivacion.setDiagnostico("se me muere AYUDAAAAA");
        derivacion.setEstado("En curso");
        derivacion.setPaciente(paciente);
        derivacion.setParaQueSector("terapia");
        derivacion.setUrgente(true);

        session().save(derivacion);

        traslado.setDerivacion(derivacion);
        traslado.setDireccionDestino("Parral 4031");
        traslado.setDireccionOrigen("Madariaga 3935");

        repositorioTraslado.guardarTraslado(traslado);

        assertThat(traslado.getId()).isNotNull();
    }

    @Test
    @Transactional @Rollback
    public void ModificarTraslado(){

        repositorioTraslado = new RepositorioTrasladoImpl(session().getSessionFactory());

        Paciente paciente = new Paciente();
        Cobertura cobertura = new Cobertura();
        Derivacion derivacion = new Derivacion();
        Traslado traslado = new Traslado();

        paciente.setDocumento(39830167);
        paciente.setFechaNacimiento(new Date());
        paciente.setNombreCompleto("Matias Guerrero");

        cobertura.setNombre("OSDE");

        session().save(paciente);
        session().save(cobertura);

        derivacion.setFechaDerivacion(new Date());
        derivacion.setCobertura(cobertura);
        derivacion.setDiagnostico("se me muere AYUDAAAAA");
        derivacion.setEstado("En curso");
        derivacion.setPaciente(paciente);
        derivacion.setParaQueSector("terapia");
        derivacion.setUrgente(true);

        session().save(derivacion);

        traslado.setDerivacion(derivacion);
        traslado.setDireccionDestino("Parral 4031");
        traslado.setDireccionOrigen("Madariaga 3935");

        session().save(traslado);

        traslado.setDireccionOrigen("Jose Marmol 4051");

        repositorioTraslado.modificarTraslado(traslado);

        assertThat(traslado.getDireccionOrigen()).isEqualTo("Jose Marmol 4051");
    }
}
