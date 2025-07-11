package com.perfumeria.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_administrador")
    private Integer idAdministrador;

    private LocalDate fecha;
    
    private Double monto;
     // Campo para almacenar el objeto Cliente asociado de forma temporal.
    // @Transient evita que JPA intente guardarlo en la base de datos.
    @Transient
    private Cliente cliente;
}