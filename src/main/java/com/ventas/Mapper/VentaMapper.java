package com.ventas.Mapper;

import com.ventas.DTO.VentaDTO;
import com.ventas.Model.Venta;

public class VentaMapper {
public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setIdCliente(venta.getIdCliente());
        dto.setIdVendedor(venta.getIdVendedor());
        dto.setFechaVenta(venta.getFechaVenta());

        return dto;
    }

    public static Venta toEntity(VentaDTO dto) {
        if (dto == null) return null;

        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFechaVenta(dto.getFechaVenta());

        return venta;
    }
}
