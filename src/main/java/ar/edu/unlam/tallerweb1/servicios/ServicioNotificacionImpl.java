package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.modelo.Traslado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
@Service("servicioNotificacion")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion{

	private RepositorioNotificacion repositorioNotificacion;
	private ServicioNotificacionUsuario servicioNotificacionUsuario;
	
	@Autowired
	public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacion, ServicioNotificacionUsuario servicioNotificacionUsuario) {
		this.repositorioNotificacion=repositorioNotificacion;
		this.servicioNotificacionUsuario=servicioNotificacionUsuario;
	}
	
	@Override
	public void guardarNotificacion(Notificacion notificacion) {
		notificacion.setFecha(new Date());
		repositorioNotificacion.guardarNotificacion(notificacion); 
	}

	@Override
	public void guardarNotificacion(SolicitudDerivacion solicitudDerivacion, String funcion, String mensaje) {
		Notificacion notificacion = new Notificacion();

		switch (funcion.toUpperCase()){

			case "G": {
				notificacion.setTitulo("Se ha generado una nueva solicitud de derivación");
				notificacion.setMensaje("Se ha generado una nueva solicitud de derivación para el centro medico " +solicitudDerivacion.getCentroMedico()
				     +" perteneciente al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto());
				notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionAdministrativos(solicitudDerivacion.getCentroMedico(), notificacion);
				break;
			}

			case "A":{
				notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
				notificacion.setTitulo("Se ha aceptado la solicitud de Derivación numero "+solicitudDerivacion.getId());
				notificacion.setMensaje("La solicitud realizada al centro médico "+solicitudDerivacion.getCentroMedico().getNombre()
						+" para derivar al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto()
						+ " ha sido aceptada ya puede generar el traslado correspondiente");
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionDerivadores(solicitudDerivacion.getDerivacion().getCobertura(), notificacion);
				break;
			}

			case "R":{
				notificacion.setDerivacion(solicitudDerivacion.getDerivacion());
				notificacion.setTitulo("Se ha rechazado la solicitud de Derivación numero "+solicitudDerivacion.getId());
				notificacion.setMensaje("La solicitud realizada al centro médico "+solicitudDerivacion.getCentroMedico().getNombre()
						+" para derivar al paciente "+solicitudDerivacion.getDerivacion().getPaciente().getNombreCompleto()
						+ " ha sido rechazada por favor buscar otro centro médico");
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionDerivadores(solicitudDerivacion.getDerivacion().getCobertura(), notificacion);
				break;
			}

			default: {
				break;
			}
		}
	}

	@Override
	public void guardarNotificacion(Derivacion derivacion, String funcion,String mensaje) {
		Notificacion notificacion = new Notificacion();

		switch (funcion.toUpperCase()){

			case "U": {
				notificacion.setDerivacion(derivacion);
				notificacion.setTitulo("Se ha generado una Derivacion Urgente");
				notificacion.setMensaje("Se ha generado una derivacion urgente para el paciente " +derivacion.getPaciente().getNombreCompleto()
						+" , por favor buscar lo antes posible un centro medico para poder generar un traslado");
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionDerivadores(derivacion.getCobertura(), notificacion);
				break;
			}

			default:{
				break;
			}
		}

	}

	@Override
	public void guardarNotificacion(Traslado traslado, String funcion,String mensaje) {
		Notificacion notificacion = new Notificacion();

		switch (funcion.toUpperCase()){
			case "G": {
				notificacion.setTraslado(traslado);
				notificacion.setTitulo("Se ha generado un nuevo traslado");
				notificacion.setMensaje("Se ha generado el traslado "+traslado.getId()+ " correspondiente a la derivación "+traslado.getDerivacion().getId() +
						" con destino al centro médico "+traslado.getCentroMedico().getNombre()+ " situado en "+traslado.getCentroMedico().getDireccion());
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionUsuario(notificacion, traslado.getDerivacion().getAutor().getId());
				servicioNotificacionUsuario.guardarNotificacionAdministrativos(traslado.getCentroMedico(), notificacion);
				break;
			}

			case "F": {
				notificacion.setTraslado(traslado);
				notificacion.setTitulo("Ha finalizado un traslado");
				notificacion.setMensaje("Ha finalizado el traslado "+traslado.getId()+ " correspondiente a la derivación "+traslado.getDerivacion().getId() +
						" con destino al centro médico "+traslado.getCentroMedico().getNombre()+ " situado en "+traslado.getCentroMedico().getDireccion());
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionUsuario(notificacion, traslado.getDerivacion().getAutor().getId());
				servicioNotificacionUsuario.guardarNotificacionDerivadores(traslado.getDerivacion().getCobertura(), notificacion);
				break;
			}

			case "C": {
				notificacion.setTraslado(traslado);
				notificacion.setTitulo("Se ha cancelado un traslado");
				notificacion.setMensaje("Se ha cancelado el traslado "+traslado.getId()+" correspondiente a la derivación "+traslado.getDerivacion().getId() +
						" con destino al centro médico "+traslado.getCentroMedico().getNombre()+ " situado en "+traslado.getCentroMedico().getDireccion()+" Motivo: "+ mensaje);
				this.guardarNotificacion(notificacion);
				servicioNotificacionUsuario.guardarNotificacionUsuario(notificacion, notificacion.getDerivacion().getAutor().getId());
				servicioNotificacionUsuario.guardarNotificacionDerivadores(traslado.getDerivacion().getCobertura(), notificacion);
				break;
			}

			default:{
				break;
			}
		}
	}

	@Override
	public Notificacion mostrarNotificacion(Long id) throws Exception {
		if (repositorioNotificacion.mostrarNotificacion(id) !=null) {
			return repositorioNotificacion.mostrarNotificacion(id);
		}
		throw new Exception("Hubo un error al buscar los datos");
	}

}
