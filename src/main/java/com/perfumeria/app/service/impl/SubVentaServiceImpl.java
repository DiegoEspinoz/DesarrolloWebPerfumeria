package com.perfumeria.app.service.impl;

import com.perfumeria.app.dto.SubVentaDTO;
import com.perfumeria.app.model.Producto;
import com.perfumeria.app.model.SubVenta;
import com.perfumeria.app.model.Venta;
import com.perfumeria.app.repository.ProductoRepository;
import com.perfumeria.app.repository.SubVentaRepository;
import com.perfumeria.app.repository.VentaRepository;
import com.perfumeria.app.service.SubVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubVentaServiceImpl implements SubVentaService {

    @Autowired
    private SubVentaRepository subVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public void guardarSubVenta(SubVentaDTO subVentaDTO) {
        // Verificar que el producto existe
        Optional<Producto> productoOpt = productoRepository.findById(subVentaDTO.getIdProducto());
        if (!productoOpt.isPresent()) {
            throw new RuntimeException("El producto con ID " + subVentaDTO.getIdProducto() + " no existe");
        }
        
        Producto producto = productoOpt.get(); // Obtener el producto de la Optional

        if (!producto.getStock()) {
            throw new RuntimeException("El producto no está disponible en stock");
        }

        // Crear y guardar la subventa
        SubVenta subVenta = new SubVenta();
        subVenta.setIdVenta(subVentaDTO.getIdVenta());
        subVenta.setIdProducto(subVentaDTO.getIdProducto());
        subVenta.setCantidad(subVentaDTO.getCantidad());
        subVentaRepository.save(subVenta);
        
        // Actualizar el monto total de la venta
        actualizarMontoVenta(subVentaDTO.getIdVenta());
    }

    @Override
    public List<SubVentaDTO> obtenerSubVentasPorVenta(Integer idVenta) {
        return subVentaRepository.findByIdVenta(idVenta).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarMontoVenta(Integer idVenta) {
        // Obtener todas las subventas de esta venta
        List<SubVenta> subVentas = subVentaRepository.findByIdVenta(idVenta);
        
        // Calcular el monto total
        double montoTotal = 0.0;
        for (SubVenta subVenta : subVentas) {
            Optional<Producto> productoOpt = productoRepository.findById(subVenta.getIdProducto());
            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                montoTotal += producto.getPrecio() * subVenta.getCantidad();
            }
        }
        
        // Actualizar el monto de la venta
        Optional<Venta> ventaOpt = ventaRepository.findById(idVenta);
        if (ventaOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            venta.setMonto(montoTotal);
            ventaRepository.save(venta);
        }
    }

    private SubVentaDTO convertirADTO(SubVenta subVenta) {
        SubVentaDTO dto = new SubVentaDTO();
        dto.setId(subVenta.getId());
        dto.setIdVenta(subVenta.getIdVenta());
        dto.setIdProducto(subVenta.getIdProducto());
        dto.setCantidad(subVenta.getCantidad());
        
        // Obtener información del producto
        productoRepository.findById(subVenta.getIdProducto()).ifPresent(producto -> {
            dto.setNombreProducto(producto.getNombre());
            dto.setPrecioProducto(producto.getPrecio());
            dto.setSubtotal(producto.getPrecio() * subVenta.getCantidad());
        });
        
        return dto;
    }
}
