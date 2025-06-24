package com.ventas.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.ventas.DTO.VentaDTO;
import com.ventas.Service.VentaService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<VentaDTO>> getAllVentas() {
        List<VentaDTO> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getVentaById(@PathVariable Integer id) {
        VentaDTO venta = ventaService.getVentaById(id);
        if (venta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("El id: " + id + " no ha podido ser encontrado"));
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping("/admin/")
    public ResponseEntity<?> createVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO creada = ventaService.createVenta(ventaDTO);
        if (creada == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Mensaje("La venta no ha podido ser creada"));
        }
        return ResponseEntity.ok(new Mensaje("Venta creada exitosamente: " + creada.getIdVenta()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody VentaDTO ventaDTO) {
        VentaDTO actualizada = ventaService.updateVenta(id, ventaDTO);
        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("No ha podido ser encontrada la venta: "+ id));
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id) {
        boolean eliminado = ventaService.deleteVenta(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("No ha podido ser encontrada la Venta: " + id));
        }
        return ResponseEntity.ok(new Mensaje("Venta: "+id+" eliminada con exito!"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class  Mensaje {
        private String mensaje;
    }
}
