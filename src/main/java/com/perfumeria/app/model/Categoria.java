package com.perfumeria.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
}
