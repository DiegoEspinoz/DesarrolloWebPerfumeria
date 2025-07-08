package com.perfumeria.app.service;

import com.perfumeria.app.dto.CategoriaDTO;
import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listarCategorias(); // Método para listar todas las categorías
    CategoriaDTO obtenerCategoria(Integer id); // Método para obtener una categoría por su ID
    void guardarCategoria(CategoriaDTO categoriaDTO); // Método para guardar una nueva categoría
}
