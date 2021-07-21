package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Adjunto;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicioAdjunto {
    void guardarImagen(MultipartFile file, String path, Derivacion derivacion, String titulo);
    void guardarImagen(MultipartFile file, String path, SolicitudDerivacion solicitudDerivacion, String titulo);
    List<Adjunto> listarAdjuntosPorDerivacion(Derivacion derivacion);
    List<Adjunto> listarAdjuntosPorSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion);
}
