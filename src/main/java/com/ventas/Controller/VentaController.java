package com.ventas.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.ventas.Model.Venta;
import com.ventas.Service.VentaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        return ventaService.getVentaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venta createVenta(@RequestBody Venta venta) {
        return ventaService.createVenta(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta ventaDetails) {
        Venta updated = ventaService.updateVenta(id, ventaDetails);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        boolean deleted = ventaService.deleteVenta(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
