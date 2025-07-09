package com.perfumeria.app.service;

import com.perfumeria.app.dto.ClienteDTO;
import com.perfumeria.app.model.Cliente;
import java.util.List;

public interface ClienteService {
    void guardarCliente(ClienteDTO clienteDTO);
    List<Cliente> obtenerClientes();
}
