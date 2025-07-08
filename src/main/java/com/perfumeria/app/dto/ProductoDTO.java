package com.perfumeria.app.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer id;
    private Integer idCategoria;
    private String nombreCategoria;
    private String nombre;
    private Double precio;
    private Boolean stock;
}
