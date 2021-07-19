package ar.edu.unlam.tallerweb1.derivaciones;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorDerivaciones;

import ar.edu.unlam.tallerweb1.modelo.Cobertura;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ControladorDerivacionesTest extends SpringTest {

    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private ServicioDerivacion servicioDerivacionMock;
    private ServicioPaciente servicioPacienteMock;
    private ServicioCobertura servicioCoberturaMock;
    private ServicioPlan servicioPlanMock;
    private ServicioNotificacionUsuario servicioNotiUsuarioMock;
    private ServicioCentroMedico servicioCentroMedicoMock;
    private ServicioSolicitudDerivacion servicioSolicitudDerivacionMock;
    private ControladorDerivaciones controlador;
    private ServicioComentario servicioComentarioMock;


    @Before
    public void init(){
        requestMock = mock(HttpServletRequest.class);
        servicioDerivacionMock = mock(ServicioDerivacion.class);
        servicioPacienteMock = mock(ServicioPaciente.class);
        servicioCoberturaMock = mock(ServicioCobertura.class);
        servicioPlanMock = mock(ServicioPlan.class);
        servicioNotiUsuarioMock = mock(ServicioNotificacionUsuario.class);
        servicioCentroMedicoMock = mock(ServicioCentroMedico.class);
        servicioComentarioMock = mock(ServicioComentario.class);
        servicioSolicitudDerivacionMock = mock(ServicioSolicitudDerivacion.class);
        sessionMock = mock(HttpSession.class);

        controlador = new ControladorDerivaciones(servicioDerivacionMock, servicioPacienteMock, servicioCoberturaMock, servicioPlanMock, servicioNotiUsuarioMock, servicioCentroMedicoMock,servicioSolicitudDerivacionMock, servicioComentarioMock);
    }

    @Test
    public void redireccionRolNullALoginTest() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("ROL")).thenReturn(null);
        ModelAndView derivaciones = controlador.derivaciones(requestMock);
        assertThat(derivaciones.getViewName().equals("redirect:/login"));
    }

    @Test
    public void redireccionRolDistintoADerivadorARouterTest() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("ROL")).thenReturn("OtroRol");
        ModelAndView derivaciones = controlador.derivaciones(requestMock);
        assertThat(derivaciones.getViewName().equals("redirect:/router"));
    }

    @Test
    public void redireccionRolDerivadorAVistaDerivacionesTest() {
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("ROL")).thenReturn("Derivador");
        when(sessionMock.getAttribute("ID_COBERTURA")).thenReturn(1L);
        when(servicioCoberturaMock.obtenerCoberturaPorId(1L)).thenReturn(null);
        when(servicioDerivacionMock.derivacionesPorCobertura(null)).thenReturn(new ArrayList<Derivacion>());
        ModelAndView derivaciones = controlador.derivaciones(requestMock);
        assertThat(derivaciones.getViewName().equals("Derivaciones/derivaciones"));
    }

    @Test
    public void queSePuedaIrALaVistaAgregarDerivacionTest() {
        Long idPaciente = 1L;
        Paciente pacienteMock = mock(Paciente.class);
        controlador.nuevaDerivacion(idPaciente, requestMock);
        when(servicioPacienteMock.obtenerPacientePorId(idPaciente)).thenReturn(pacienteMock);
        when(servicioPlanMock.obetenerCoberturasPaciente(idPaciente)).thenReturn(new HashSet<Cobertura>());
        assertThat(controlador.nuevaDerivacion(idPaciente, requestMock).getViewName().equals("Derivaciones/agregar-derivacion"));
    }

    @Test
    public void queElModeloTengaLosAtributosNecesariosParaCrearDerivacionTest() {
        Long idPaciente = 1L;
        Paciente pacienteMock = mock(Paciente.class);
        controlador.nuevaDerivacion(idPaciente, requestMock);
        when(servicioPacienteMock.obtenerPacientePorId(idPaciente)).thenReturn(pacienteMock);
        when(servicioPlanMock.obetenerCoberturasPaciente(idPaciente)).thenReturn(new HashSet<Cobertura>());
        ModelAndView modelAndView = controlador.nuevaDerivacion(idPaciente, requestMock);
        ModelMap modelMap = modelAndView.getModelMap();
        List<String> sectores = (List<String>)modelMap.get("sectores");
        assertThat(!sectores.isEmpty());
        Derivacion derivacion = (Derivacion) modelMap.get("derivacion");
        assertThat(derivacion).isNotNull();
        Paciente paciente = (Paciente) modelMap.get("paciente");
        assertThat(paciente).isNotNull();
        HashSet<Cobertura> coberturas = (HashSet<Cobertura>)modelMap.get("coberturas");
        assertThat(!coberturas.isEmpty());
    }
}