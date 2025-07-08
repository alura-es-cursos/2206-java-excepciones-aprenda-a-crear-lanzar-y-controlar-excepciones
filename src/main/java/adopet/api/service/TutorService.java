package adopet.api.service;

import adopet.api.dto.RegistroTutorDTO;
import adopet.api.dto.TutorDTO;
import adopet.api.model.Tutor;
import adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public List<TutorDTO> listarTodos(){
        return repository.findAll().stream().map(TutorDTO::new).toList();
    }
    public void registrar(RegistroTutorDTO datos){
        repository.save(new Tutor(datos));
    }
}
