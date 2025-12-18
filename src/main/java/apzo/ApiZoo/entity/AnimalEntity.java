package apzo.ApiZoo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "animales")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long idAnimal;

    @Column(name = "nombre_animal", length = 50)
    private String nombreAnimal;

    @Column(length = 255, nullable = false)
    private String detalles;

    @Column(length = 500, nullable = false)
    private String descripcion;

    @Column(length = 50, nullable = false)
    private String habitad;

    @Column(length = 50, nullable = false)
    private String clima;

    @Column(length = 512, nullable = false)
    private String img;
}