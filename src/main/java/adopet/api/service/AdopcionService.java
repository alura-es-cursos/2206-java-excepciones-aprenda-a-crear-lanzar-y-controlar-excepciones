package adopet.api.service;

import adopet.api.dto.*;
import adopet.api.exception.AdopcionException;
import adopet.api.model.Adopcion;
import adopet.api.model.Pet;
import adopet.api.model.StatusAdopcion;
import adopet.api.model.Tutor;
import adopet.api.repository.AdopcionRepository;
import adopet.api.repository.PetRepository;
import adopet.api.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopcionService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AdopcionRepository adopcionRepository;

    public List<AdopcionDTO> listarTodos(){

        return adopcionRepository.findAll().stream().map(AdopcionDTO::new).toList();
    }
    public AdopcionDTO listar(Long id){

        return adopcionRepository.findById(id).stream().findFirst().map(AdopcionDTO::new).orElse(null);
    }

    public void solicitar(SolicitudDeAdopcionDTO dto){
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());
        //- Pet ya fue adoptado;
        if (pet.getAdoptado()){
            throw new AdopcionException("Pet ya adoptado");
        }

        //- Pet con solicitación de adopción AGUARDANDO_ANALISIS;
        Boolean petAdopcionAnalisis = adopcionRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdopcion.AGUARDANDO_ANALISIS);
        if (petAdopcionAnalisis){
            throw new AdopcionException("Pet con adopción en análisis");
        }

        //- Tutor con 2 adopciones aprobadas.
        Integer tutorAdopciones = adopcionRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdopcion.APROBADO);
        if (tutorAdopciones == 2){
            throw new AdopcionException("Tutor con máximo de adopciones");
        }

        adopcionRepository.save(new Adopcion(tutor,pet, dto.motivo()));
    }

    public void aprobar(AprobarAdopcionDTO dto){
        Adopcion adopcion = adopcionRepository.getReferenceById(dto.idAdopcion());
        adopcion.marcarComoAprobada();
        adopcion.getPet().marcarComoAdoptado();
    }

    public void rechazar(RechazarAdopcionDTO dto){
        Adopcion adopcion = adopcionRepository.getReferenceById(dto.idAdopcion());
        adopcion.marcarComoRechazada(dto.justificacion());
    }
}
