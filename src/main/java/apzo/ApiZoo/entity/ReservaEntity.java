package apzo.ApiZoo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "reserva")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "fecha_ing", nullable = false)
    private LocalDate fechaIng;

    @Column(name = "fecha_comp", nullable = false)
    private LocalDate fechaComp;

    @Column(name = "num_personas", nullable = false)
    private Short numPersonas;  // SMALLINT → Short

    @Column(name = "valor_por_persona", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPorPersona;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private BigDecimal valorTotal;  // Generado en BD, pero lo mapeamos para leerlo

    @Column(length = 100, nullable = false)
    private String email;
}