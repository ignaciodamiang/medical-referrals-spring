package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
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
        CentroMedico centroMedico = new CentroMedico();

        paciente.setDocumento(39830167);
        paciente.setFechaNacimiento(new Date());
        paciente.setNombreCompleto("Matias Guerrero");

        cobertura.setNombre("OSDE");

        session().save(paciente);
        session().save(cobertura);

        derivacion.setFechaDerivacion(new Date());
        derivacion.setCobertura(cobertura);
        derivacion.setDiagnostico("se me muere AYUDAAAAA");
        derivacion.setFinalizada(false);
        derivacion.setPaciente(paciente);
        derivacion.setParaQueSector("terapia");
        derivacion.setUrgente(true);

        session().save(derivacion);

        centroMedico.setNombre("san juan de dios");
        centroMedico.setGuardia(true);
        centroMedico.setSalaComun(true);
        centroMedico.setTerapia(true);
        centroMedico.setDireccion("Madariaga 3935");

        session().save(centroMedico);

        traslado.setDerivacion(derivacion);
        traslado.setDireccionOrigen("Madariaga 3935");
        traslado.setCentroMedico(centroMedico);

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
        CentroMedico centroMedico = new CentroMedico();

        paciente.setDocumento(39830167);
        paciente.setFechaNacimiento(new Date());
        paciente.setNombreCompleto("Matias Guerrero");

        cobertura.setNombre("OSDE");

        session().save(paciente);
        session().save(cobertura);

        derivacion.setFechaDerivacion(new Date());
        derivacion.setCobertura(cobertura);
        derivacion.setDiagnostico("se me muere AYUDAAAAA");
        derivacion.setFinalizada(false);
        derivacion.setPaciente(paciente);
        derivacion.setParaQueSector("terapia");
        derivacion.setUrgente(true);

        session().save(derivacion);

        centroMedico.setNombre("san juan de dios");
        centroMedico.setGuardia(true);
        centroMedico.setSalaComun(true);
        centroMedico.setTerapia(true);
        centroMedico.setDireccion("Madariaga 3935");

        session().save(centroMedico);

        traslado.setDerivacion(derivacion);
        traslado.setDireccionOrigen("Madariaga 3935");
        traslado.setCentroMedico(centroMedico);

        session().save(traslado);

        traslado.setDireccionOrigen("Jose Marmol 4051");

        repositorioTraslado.modificarTraslado(traslado);

        assertThat(traslado.getDireccionOrigen()).isEqualTo("Jose Marmol 4051");
    }
}
