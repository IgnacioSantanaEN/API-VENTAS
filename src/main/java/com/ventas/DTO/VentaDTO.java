package com.ventas.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private LocalDate fechaVenta;
}
