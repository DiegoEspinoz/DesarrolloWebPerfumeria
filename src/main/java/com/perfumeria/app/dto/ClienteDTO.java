package com.perfumeria.app.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private String distrito;
    private String sexo;
    private String dni;
}