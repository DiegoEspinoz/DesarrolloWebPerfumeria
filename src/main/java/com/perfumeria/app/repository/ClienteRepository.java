package com.perfumeria.app.repository;

import com.perfumeria.app.model.Cliente;

import java.util.Optional; // Importa Optional para manejar valores que pueden estar ausentes

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);
}
