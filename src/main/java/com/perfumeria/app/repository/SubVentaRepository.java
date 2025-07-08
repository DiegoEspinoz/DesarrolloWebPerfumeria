package com.perfumeria.app.repository;

import com.perfumeria.app.model.SubVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubVentaRepository extends JpaRepository<SubVenta, Integer> {
    List<SubVenta> findByIdVenta(Integer idVenta);
}