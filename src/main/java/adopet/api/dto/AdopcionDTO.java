package adopet.api.dto;

import adopet.api.model.Adopcion;
import adopet.api.model.StatusAdopcion;

public record AdopcionDTO(Long id, Long tutor, Long pet, String motivo, StatusAdopcion status,
                          String justificacion) {
    public AdopcionDTO(Adopcion adopcion){
        this(adopcion.getId(), adopcion.getTutor().getId(), adopcion.getPet().getId(), adopcion.getMotivo(), adopcion.getStatus(), adopcion.getJustificacion());
    }
}
