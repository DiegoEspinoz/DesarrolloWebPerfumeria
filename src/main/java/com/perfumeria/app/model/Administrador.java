package com.perfumeria.app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @Id // Identificador único para el administrador
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;

    private String nombre;
    private String contraseña;
}