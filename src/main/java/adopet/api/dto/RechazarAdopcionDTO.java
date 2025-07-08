package adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RechazarAdopcionDTO(
        @NotNull Long idAdopcion,
        @NotBlank String justificacion
) {
}
