package apzo.ApiZoo.dto;

import lombok.Data;

import java.math.BigDecimal;

// ReservaResponseDTO.java
@Data
public class ReservaResponseDTO extends ReservaDTO {
    private Long idReserva;
    private BigDecimal valorTotal;  // Incluimos el calculado
}
