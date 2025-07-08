package com.perfumeria.app.repository;

import com.perfumeria.app.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    @Query("SELECT p FROM Producto p WHERE p.stock = true AND LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%'))") // Método para buscar productos disponibles por término
    List<Producto> buscarProductosDisponibles(String termino);
}
