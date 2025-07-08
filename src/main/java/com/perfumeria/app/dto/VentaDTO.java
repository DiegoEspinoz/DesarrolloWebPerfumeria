package com.perfumeria.app.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VentaDTO {
    private Integer id;
    private String dniCliente;
    private Integer idCliente;
    private Integer idAdministrador;
    private LocalDate fecha;
    private BigDecimal monto;
}
