package adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageStorageService {

    private static final String DIR_UPLOAD = System.getProperty("user.dir") + "/src/main/resources/storage/";

    public String upload(MultipartFile imagem) throws IOException {

        String nuevoNombre = this.generarNuevoNombre(imagem.getOriginalFilename());

        String rutaCompletaDelArchivo = DIR_UPLOAD + nuevoNombre;

        imagem.transferTo(new File(rutaCompletaDelArchivo));

        return nuevoNombre;
    }

    private String generarNuevoNombre(String nombreOriginal)
    {
        String[] nombreSplit = nombreOriginal.split("\\.");
        String extension = "." + nombreSplit[1];

        return UUID.randomUUID() + extension;
    }

}
