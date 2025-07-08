package com.perfumeria.app.service;

import com.perfumeria.app.dto.VentaDTO;

public interface VentaService {
    void guardarVenta(VentaDTO ventaDTO);
    Integer obtenerIdClientePorDni(String dni);
    Integer obtenerUltimoAdministrador();
    Integer obtenerUltimaVenta();
}
