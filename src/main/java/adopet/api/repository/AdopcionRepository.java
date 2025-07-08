package adopet.api.repository;

import adopet.api.model.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopcionRepository extends JpaRepository<Adopcion,Long> {

}
