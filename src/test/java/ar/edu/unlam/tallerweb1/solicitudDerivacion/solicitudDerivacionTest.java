package ar.edu.unlam.tallerweb1.solicitudDerivacion;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class solicitudDerivacionTest extends SpringTest {
    @Test
    @Transactional
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }


    @Test
    @Transactional @Rollback
    public void guardaSolicitudDerivacion(){
        SolicitudDerivacion solicitudDerivacion = new SolicitudDerivacion();
        solicitudDerivacion.setConfirmado(true);
        session().save(solicitudDerivacion);
        assertThat(solicitudDerivacion.getId()).isNotNull();
    }
}
