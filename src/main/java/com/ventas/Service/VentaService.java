package com.ventas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.Model.Venta;
import com.ventas.Repository.VentaRepository;

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

    public Venta updateVenta(Integer id, Venta venta) {
        return ventaRepository.findById(id).map(Venta -> {
            venta.setIdCliente(venta.getIdCliente());
            venta.setIdVendedor(venta.getIdVendedor());
            venta.setFechaVenta(venta.getFechaVenta());
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
