package com.perfumeria.app.repository;

import com.perfumeria.app.model.Administrador;

import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para operaciones CRUD

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByNombreAndContraseña(String nombre, String contraseña); // Método para buscar un administrador por nombre y contraseña
}