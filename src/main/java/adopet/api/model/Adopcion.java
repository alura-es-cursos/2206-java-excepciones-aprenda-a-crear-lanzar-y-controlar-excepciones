package adopet.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adopciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdopcion status;

    private String justificacion;

    public Adopcion(Tutor tutor, Pet pet, String motivo){
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.status = StatusAdopcion.AGUARDANDO_ANALISIS;
    }

    public void marcarComoAprobada(){

        this.status = StatusAdopcion.APROBADO;
    }

    public void marcarComoRechazada(String justificacion)
    {
        this.status = StatusAdopcion.RECHAZADO;
        this.justificacion = justificacion;
    }
}
