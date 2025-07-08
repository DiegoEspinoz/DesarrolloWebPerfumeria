package com.perfumeria.app.dto;

import lombok.Data;

@Data
public class SubVentaDTO {
    private Integer id;
    private Integer idVenta;
    private Integer idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer cantidad;
    private Double subtotal;
}