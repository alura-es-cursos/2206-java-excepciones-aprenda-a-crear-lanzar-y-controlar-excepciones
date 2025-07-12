package adopet.api.repository;

import adopet.api.model.Adopcion;
import adopet.api.model.StatusAdopcion;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopcionRepository extends JpaRepository<Adopcion,Long> {

    Boolean existsByPetIdAndStatus(@NotNull Long idPet, StatusAdopcion statusAdopcion);

    Integer countByTutorIdAndStatus(@NotNull Long idTutor, StatusAdopcion statusAdopcion);
}
