package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioAdjuntoDerivacion;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service("servicioAdjuntoDerivacion")
@Transactional
public class ServicioAdjuntoDerivacionImpl implements ServicioAdjuntoDerivacion{

    private RepositorioAdjuntoDerivacion repositorioAdjuntoDerivacion;

     @Autowired
    public ServicioAdjuntoDerivacionImpl(RepositorioAdjuntoDerivacion repositorioAdjuntoDerivacion){
         this.repositorioAdjuntoDerivacion=repositorioAdjuntoDerivacion;
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


}
