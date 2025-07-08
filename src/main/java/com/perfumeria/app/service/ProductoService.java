package com.perfumeria.app.service;

import com.perfumeria.app.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarProductos();
    List<ProductoDTO> buscarProductos(String termino);
    ProductoDTO obtenerProducto(Integer id);
    void guardarProducto(ProductoDTO productoDTO);
}