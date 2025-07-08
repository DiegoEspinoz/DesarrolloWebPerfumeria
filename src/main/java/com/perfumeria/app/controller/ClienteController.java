package com.perfumeria.app.controller;

import com.perfumeria.app.dto.ClienteDTO;
import com.perfumeria.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired; // Permite la inyección automática de dependencias
import org.springframework.stereotype.Controller; // Define esta clase como un controlador MVC de Spring
import org.springframework.ui.Model; // Permite agregar atributos al modelo para pasarlos a la vista
import org.springframework.web.bind.annotation.*; // Importa todas las anotaciones para mapeo de solicitudes HTTP


@Controller // Indica que esta clase es un controlador que maneja peticiones HTTP y retorna vistas
public class ClienteController {

    @Autowired // Inyecta automáticamente una implementación del servicio ClienteService
    private ClienteService clienteService;

    @GetMapping("/clientes") // Mapea o renderiza las peticiones HTTP GET a la ruta /clientes
    public String mostrarClientes(Model model) {
        model.addAttribute("cliente", new ClienteDTO()); // Agrega un objeto ClienteDTO vacío al modelo para el formulario
        model.addAttribute("paginaActual", "clientes"); // Indica la página actual para marcar el menú activo
        return "clientes"; // Retorna el nombre de la plantilla Thymeleaf (clientes.html)
    }

    @PostMapping("/clientes") // Mapea las peticiones HTTP POST a la ruta /clientes
    public String guardarCliente(@ModelAttribute("cliente") ClienteDTO clienteDTO, Model model) {
        clienteService.guardarCliente(clienteDTO);// Invoca al servicio para guardar el cliente
        return "redirect:/clientes"; // Redirecciona al usuario a la página de clientes
    }
}