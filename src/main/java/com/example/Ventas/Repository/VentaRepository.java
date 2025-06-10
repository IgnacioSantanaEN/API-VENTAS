package com.example.Ventas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ventas.Model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer>{

}
