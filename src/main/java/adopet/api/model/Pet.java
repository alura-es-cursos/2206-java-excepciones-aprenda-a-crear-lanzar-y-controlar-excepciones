package adopet.api.model;


import adopet.api.dto.RegistroPetDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pets")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer edad;

    @Enumerated(EnumType.STRING)
    private TipoPet tipo;

    private Boolean adoptado;

    private String imagen;

    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    private Adopcion adopcion;

    public Pet(RegistroPetDTO dados, String nombreImagen)
    {
        this.nombre = dados.nombre();
        this.edad = dados.edad();
        this.tipo = dados.tipo();
        this.imagen = nombreImagen;
        this.adoptado = false;
    }

    public void marcarComoAdoptado(){
        this.adoptado = true;
    }
}
