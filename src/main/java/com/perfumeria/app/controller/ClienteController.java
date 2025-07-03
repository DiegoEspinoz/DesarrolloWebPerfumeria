package com.perfumeria.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ClienteController {

    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("paginaActual", "clientes");
        return "clientes"; // Carga clientes.html
    }
}