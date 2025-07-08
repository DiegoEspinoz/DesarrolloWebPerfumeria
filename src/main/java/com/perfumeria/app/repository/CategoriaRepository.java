package com.perfumeria.app.repository;

import com.perfumeria.app.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotación para indicar que esta interfaz es un repositorio
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
