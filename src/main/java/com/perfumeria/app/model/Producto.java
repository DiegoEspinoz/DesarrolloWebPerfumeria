package com.perfumeria.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    private String nombre;
    private Double precio;
    private Boolean stock;
}
