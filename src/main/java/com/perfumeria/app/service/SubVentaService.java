package com.perfumeria.app.service;

import com.perfumeria.app.dto.SubVentaDTO;
import java.util.List;

public interface SubVentaService {
    void guardarSubVenta(SubVentaDTO subVentaDTO);
    List<SubVentaDTO> obtenerSubVentasPorVenta(Integer idVenta);
    void actualizarMontoVenta(Integer idVenta);
}
