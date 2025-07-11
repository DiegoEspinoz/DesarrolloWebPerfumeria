package com.perfumeria.app.repository;

import com.perfumeria.app.model.Venta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}