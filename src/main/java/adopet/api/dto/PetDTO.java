package adopet.api.dto;

import adopet.api.model.Pet;
import adopet.api.model.TipoPet;

public record PetDTO(Long id, String nombre, Integer edad, TipoPet tipo, Boolean adotado, String imagen) {

    public PetDTO(Pet pet){

        this(pet.getId(), pet.getNombre(), pet.getEdad(), pet.getTipo(), pet.getAdoptado(), pet.getImagen());
    }
}
