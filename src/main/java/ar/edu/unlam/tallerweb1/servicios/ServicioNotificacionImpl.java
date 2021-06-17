package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
@Service("servicioNotificacion")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion{

	private RepositorioNotificacion repositorioNotificacion;
	
	@Autowired
	public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacion) {
		this.repositorioNotificacion=repositorioNotificacion;
	}
	
	@Override
	public void guardarNotificacion(Notificacion notificacion, HttpServletRequest request) {
		notificacion.setFecha(new Date());
		repositorioNotificacion.guardarNotificacion(notificacion); 
	}

	@Override
	public Notificacion mostrarNotificacion(Long id) throws Exception {
		if (repositorioNotificacion.mostrarNotificacion(id) !=null) {
			return repositorioNotificacion.mostrarNotificacion(id);
		}
		throw new Exception("Hubo un error al buscar los datos");
	}

}
