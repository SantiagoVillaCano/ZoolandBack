package apzo.ApiZoo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReservaDTO {
    private LocalDate fechaIng;
    private LocalDate fechaComp;
    private Short numPersonas;
    private BigDecimal valorPorPersona;
    private String email;
}

