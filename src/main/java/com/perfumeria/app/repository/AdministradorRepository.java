package com.perfumeria.app.repository;

import com.perfumeria.app.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByNombreAndContraseña(String nombre, String contraseña);
}