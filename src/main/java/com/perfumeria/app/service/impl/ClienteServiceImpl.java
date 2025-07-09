package com.perfumeria.app.service.impl;

import com.perfumeria.app.dto.ClienteDTO;
import com.perfumeria.app.model.Cliente;
import com.perfumeria.app.repository.ClienteRepository;
import com.perfumeria.app.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void guardarCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        cliente.setDireccion(dto.getDireccion());
        cliente.setDistrito(dto.getDistrito());
        cliente.setSexo(dto.getSexo());
        cliente.setDni(dto.getDni());
        cliente.setEstado(true); // activo por defecto
        clienteRepository.save(cliente);
    }
    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }
}