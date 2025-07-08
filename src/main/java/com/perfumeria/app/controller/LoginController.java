package com.perfumeria.app.controller;

import com.perfumeria.app.dto.LoginDTO;
import com.perfumeria.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO()); // Agrega un objeto LoginDTO vacío al modelo para el formulario
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
        boolean valid = authService.login(loginDTO.getNickname(), loginDTO.getPassword()); // Llama al servicio de autenticación para validar las credenciales
        if (valid) {
            return "redirect:/clientes";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "clientes"; // Redirige a la página de clientes como página de inicio
    }
}