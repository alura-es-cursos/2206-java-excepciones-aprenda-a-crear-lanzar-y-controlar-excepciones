package adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistroTutorDTO(
        @NotBlank
        String nombre,

        @NotBlank
        String email
) {
}
