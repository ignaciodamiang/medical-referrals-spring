package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Adjunto;
import ar.edu.unlam.tallerweb1.modelo.Derivacion;
import ar.edu.unlam.tallerweb1.modelo.SolicitudDerivacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAdjunto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("servicioAdjunto")
@Transactional
public class ServicioAdjuntoImpl implements ServicioAdjunto{

    private RepositorioAdjunto repositorioAdjunto;

     @Autowired
    public ServicioAdjuntoImpl(RepositorioAdjunto repositorioAdjunto){
         this.repositorioAdjunto=repositorioAdjunto;
     }

    private boolean esAdjuntoValido(MultipartFile file) {

        List<String> tiposDeImagenesValidas = Arrays.asList("image/png", "image/jpeg");
        List<String> extensionesValidasDeImagen = Arrays.asList("jpg", "png");
        String extensionDeArchivo = FilenameUtils.getExtension( file.getOriginalFilename() ).toString();
        String tipoDeArchivo = file.getContentType().toString();

        if (  tiposDeImagenesValidas.contains(tipoDeArchivo) && extensionesValidasDeImagen.contains(extensionDeArchivo)) {
            return true;
        }
        return false;
    }

    @Override
    public void guardarImagen(MultipartFile file, String path, Derivacion derivacion,String titulo){
        try {

            if(!file.isEmpty() && esAdjuntoValido(file) ){
                Adjunto adjunto = new Adjunto();
                String nombreRandom = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
                Path pathcompleto = Paths.get( path + nombreRandom );
                Files.write(pathcompleto,file.getBytes());
                adjunto.setDerivacion(derivacion);
                adjunto.setFechaCreacion(new Date());
                adjunto.setTitulo(titulo);
                adjunto.setImgPath(nombreRandom);
                repositorioAdjunto.guardarAdjunto(adjunto);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void guardarImagen(MultipartFile file, String path, SolicitudDerivacion solicitudDerivacion,String titulo){
        try {

            if(!file.isEmpty() && esAdjuntoValido(file) ){
                Adjunto adjunto = new Adjunto();
                String nombreRandom = UUID.randomUUID().toString() + '.' + FilenameUtils.getExtension(file.getOriginalFilename());
                Path pathcompleto = Paths.get( path + nombreRandom );
                Files.write(pathcompleto,file.getBytes());
                adjunto.setSolicitudDerivacion(solicitudDerivacion);
                adjunto.setFechaCreacion(new Date());
                adjunto.setTitulo(titulo);
                adjunto.setImgPath(nombreRandom);
                repositorioAdjunto.guardarAdjunto(adjunto);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public List<Adjunto> listarAdjuntosPorDerivacion(Derivacion derivacion) {
        return repositorioAdjunto.obtenerAdjuntosPorDerivacion(derivacion);
    }

    @Override
    public List<Adjunto> listarAdjuntosPorSolicitudDerivacion(SolicitudDerivacion solicitudDerivacion) {
        return repositorioAdjunto.obtenerAdjuntosPorSolicitudDerivacion(solicitudDerivacion);
    }

}
