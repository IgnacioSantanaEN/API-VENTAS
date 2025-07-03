package com.ventas.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }

    public Venta getVentaById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta createVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Integer id, Venta ventaData) {
        return ventaRepository.findById(id).map(existente -> {
            existente.setIdCliente(ventaData.getIdCliente());
            existente.setIdVendedor(ventaData.getIdVendedor());
            existente.setFechaVenta(ventaData.getFechaVenta());
            return ventaRepository.save(existente);
        }).orElse(null);
    }

    public boolean deleteVenta(Integer id) {
        return ventaRepository.findById(id).map(venta -> {
            ventaRepository.delete(venta);
            return true;
        }).orElse(false);
    }
}
