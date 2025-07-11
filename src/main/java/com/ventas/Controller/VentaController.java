package com.ventas.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.ventas.DTO.VentaDTO;
import com.ventas.Model.Venta;
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
    public ResponseEntity<?> getAllVentas() {
        List<VentaDTO> ventas = ventaService.getAllVentas();

        if(ventas == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("La lista de ventas esta vacia"));
        }

        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVentaById(@PathVariable Integer id) {
        Venta venta = ventaService.getVentaById(id);
        if (venta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("El id: " + id + " no ha podido ser encontrado"));
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody Venta venta) {
        Venta creada = ventaService.createVenta(venta);
        if (creada == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Mensaje("La venta no ha podido ser creada"));
        }
        return ResponseEntity.ok(new Mensaje("Venta creada exitosamente: " + creada.getIdVenta()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        Venta actualizada = ventaService.updateVenta(id, venta);
        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("No ha podido ser encontrada la venta: " + id));
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
        return ResponseEntity.ok(new Mensaje("Venta: "+ id +" eliminada con exito!"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class  Mensaje {
        private String mensaje;
    }
}
