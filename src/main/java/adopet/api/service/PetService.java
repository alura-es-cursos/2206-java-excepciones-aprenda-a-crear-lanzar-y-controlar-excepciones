package adopet.api.service;

import adopet.api.dto.RegistroPetDTO;
import adopet.api.dto.PetDTO;
import adopet.api.model.Pet;
import adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    @Autowired
    private ImageStorageService imagenService;

    public List<PetDTO> listarTodos(){
        return repository.findAll().stream().map(PetDTO::new).toList();
    }
    public void registrar(RegistroPetDTO dto, MultipartFile imagen){

        String nombreImagen = imagenService.upload(imagen);

        repository.save(new Pet(dto, nombreImagen));
    }
}
