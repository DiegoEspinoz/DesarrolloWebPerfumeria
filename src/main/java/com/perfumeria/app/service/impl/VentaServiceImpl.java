package com.perfumeria.app.service.impl;

import com.perfumeria.app.dto.VentaDTO;
import com.perfumeria.app.model.Venta;
import com.perfumeria.app.repository.AdministradorRepository;
import com.perfumeria.app.repository.ClienteRepository;
import com.perfumeria.app.repository.VentaRepository;
import com.perfumeria.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public void guardarVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        
        // Obtener el ID del cliente a partir del DNI
        Integer idCliente = obtenerIdClientePorDni(ventaDTO.getDniCliente());
        if (idCliente == null) {
            throw new RuntimeException("No se encontró cliente con el DNI: " + ventaDTO.getDniCliente());
        }
        
        // Obtener el ID del último administrador
        Integer idAdmin = obtenerUltimoAdministrador();
        if (idAdmin == null) {
            throw new RuntimeException("No se encontró ningún administrador en el sistema");
        }
        
        venta.setIdCliente(idCliente);
        venta.setIdAdministrador(idAdmin);
        venta.setFecha(LocalDate.now());
        venta.setMonto(0.0); // Monto inicial 0.00
        
        ventaRepository.save(venta);
    }

    @Override
    public Integer obtenerIdClientePorDni(String dni) {
        return clienteRepository.findByDni(dni)
                .map(cliente -> cliente.getId())
                .orElse(null);
    }

    @Override
    public Integer obtenerUltimoAdministrador() {
        return administradorRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .findFirst()
                .map(admin -> admin.getId())
                .orElse(null);
    }
    
    @Override
    public Integer obtenerUltimaVenta() {
        return ventaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .findFirst()
                .map(venta -> venta.getId())
                .orElse(null);
    }
}