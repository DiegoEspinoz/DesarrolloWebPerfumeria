package com.perfumeria.app.controller;

import com.perfumeria.app.dto.ClienteDTO;
import com.perfumeria.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("cliente", new ClienteDTO()); // para el formulario
        model.addAttribute("paginaActual", "clientes");
        return "clientes";
    }

    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute("cliente") ClienteDTO clienteDTO, Model model) {
        clienteService.guardarCliente(clienteDTO);
        return "redirect:/clientes";
    }
}