package com.perfumeria.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private String distrito;
    private String sexo;
    private String dni;

    @Column(columnDefinition = "TINYINT(1)") // Define el tipo de columna como booleano
    private boolean estado = true;
}
