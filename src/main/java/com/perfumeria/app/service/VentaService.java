package com.perfumeria.app.service;

import com.perfumeria.app.dto.VentaDTO;
import com.perfumeria.app.model.Venta;
import java.util.List;

public interface VentaService {
    void guardarVenta(VentaDTO ventaDTO);
    Integer obtenerIdClientePorDni(String dni);
    Integer obtenerUltimoAdministrador();
    Integer obtenerUltimaVenta();
    List<Venta> obtenerVentas();

}
