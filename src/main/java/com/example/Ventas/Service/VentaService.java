package com.example.Ventas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ventas.Model.Venta;
import com.example.Ventas.Repository.VentaRepository;

@Service
public class VentaService {
     @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> getVentaById(Integer id) {
        return ventaRepository.findById(id);
    }

    public Venta createVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Integer id, Venta ventaDetails) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setIdCliente(ventaDetails.getIdCliente());
            venta.setIdVendedor(ventaDetails.getIdVendedor());
            venta.setFechaVenta(ventaDetails.getFechaVenta());
            return ventaRepository.save(venta);
        }).orElse(null);
    }

    public boolean deleteVenta(Integer id) {
        return ventaRepository.findById(id).map(venta -> {
            ventaRepository.delete(venta);
            return true;
        }).orElse(false);
    }
}
