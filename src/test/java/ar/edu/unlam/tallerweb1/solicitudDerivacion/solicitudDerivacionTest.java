package ar.edu.unlam.tallerweb1.solicitudDerivacion;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSolicitudDerivacionImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTrasladoImpl;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class solicitudDerivacionTest extends SpringTest {

    RepositorioSolicitudDerivacionImpl repositorioSolicitudDerivacion;
    @Test
    @Transactional @Rollback
    public void guardaSolicitudDerivacion(){
        repositorioSolicitudDerivacion = new RepositorioSolicitudDerivacionImpl(session().getSessionFactory());
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        solicitudDerivacion.setConfirmado(true);
        repositorioSolicitudDerivacion.guardarSolicitudDerivacion(solicitudDerivacion);
        assertThat(solicitudDerivacion.getId()).isNotNull();
    }
    @Test
    @Transactional @Rollback
    public void modificarSolicitudDerivacion() {
        repositorioSolicitudDerivacion = new RepositorioSolicitudDerivacionImpl(session().getSessionFactory());
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        solicitudDerivacion.setConfirmado(true);
        repositorioSolicitudDerivacion.guardarSolicitudDerivacion(solicitudDerivacion);
        solicitudDerivacion.setConfirmado(false);
        repositorioSolicitudDerivacion.modificarSolicitudDerivacion(solicitudDerivacion);
        assertThat(solicitudDerivacion.getConfirmado()).isEqualTo(false);
    }
}
