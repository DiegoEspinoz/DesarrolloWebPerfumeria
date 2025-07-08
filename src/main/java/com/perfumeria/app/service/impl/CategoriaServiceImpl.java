package com.perfumeria.app.service.impl;

import com.perfumeria.app.dto.CategoriaDTO;
import com.perfumeria.app.model.Categoria;
import com.perfumeria.app.repository.CategoriaRepository;
import com.perfumeria.app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerCategoria(Integer id) {
        return categoriaRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Override
    public void guardarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        if (categoriaDTO.getId() != null) {
            categoria.setId(categoriaDTO.getId());
        }
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        
        categoriaRepository.save(categoria);
    }

    private CategoriaDTO convertirADTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }
}
