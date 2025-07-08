package com.perfumeria.app.service.impl;

import com.perfumeria.app.dto.ProductoDTO;
import com.perfumeria.app.model.Producto;
import com.perfumeria.app.repository.CategoriaRepository;
import com.perfumeria.app.repository.ProductoRepository;
import com.perfumeria.app.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarProductos(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return listarProductos();
        }
        return productoRepository.buscarProductosDisponibles(termino).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerProducto(Integer id) {
        return productoRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Override
    public void guardarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        if (productoDTO.getId() != null) {
            producto.setId(productoDTO.getId());
        }
        producto.setIdCategoria(productoDTO.getIdCategoria());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        
        productoRepository.save(producto);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setIdCategoria(producto.getIdCategoria());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        
        // Obtener el nombre de la categoría
        categoriaRepository.findById(producto.getIdCategoria())
                .ifPresent(categoria -> dto.setNombreCategoria(categoria.getNombre()));
        
        return dto;
    }
}
