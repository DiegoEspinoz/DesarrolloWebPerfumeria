package com.perfumeria.app.repository;

import com.perfumeria.app.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Métodos personalizados si los necesitas
}
// Este repositorio hereda de JpaRepository, lo que proporciona métodos CRUD básicos
