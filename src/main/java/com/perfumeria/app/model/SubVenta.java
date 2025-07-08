package com.perfumeria.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subventa")
@Data
public class SubVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "id_producto")
    private Integer idProducto;

    private Integer cantidad;
}