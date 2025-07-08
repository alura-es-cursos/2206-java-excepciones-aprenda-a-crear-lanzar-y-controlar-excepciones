package adopet.api.model;

import adopet.api.dto.RegistroTutorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutores")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;

    @NotBlank
    private String email;

    @OneToMany(mappedBy = "tutor")
    private List<Adopcion> adocoes = new ArrayList<>();

    public Tutor(RegistroTutorDTO dados){
        this.nombre = dados.nombre();
        this.email = dados.email();
    }

}
