package com.ventas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.DTO.VentaDTO;
import com.ventas.Mapper.VentaMapper;
import com.ventas.Model.Venta;
import com.ventas.Repository.VentaRepository;

@Service
public class VentaService {
     @Autowired
    private VentaRepository ventaRepository;

    public List<VentaDTO> getAllVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream()
            .map(VentaMapper::toDTO)
                .toList();
    }

    public VentaDTO getVentaById(Integer id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        return VentaMapper.toDTO(venta);
    }

    public VentaDTO createVenta(VentaDTO ventaDTO) {
        Venta venta = VentaMapper.toEntity(ventaDTO);
        Venta creada = ventaRepository.save(venta);
        return VentaMapper.toDTO(creada);
    }

    public VentaDTO updateVenta(Integer id, VentaDTO ventaDTO) {
        Venta existente = ventaRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setIdCliente(ventaDTO.getIdCliente());
            existente.setIdVendedor(ventaDTO.getIdVendedor());
            existente.setFechaVenta(ventaDTO.getFechaVenta());

            Venta actualizada = ventaRepository.save(existente);
            return VentaMapper.toDTO(actualizada);
        }

        return null;
    }

    public boolean deleteVenta(Integer id) {
        return ventaRepository.findById(id).map(venta -> {
            ventaRepository.delete(venta);
            return true;
        }).orElse(false);
    }
}
