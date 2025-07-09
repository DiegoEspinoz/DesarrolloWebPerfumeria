package com.perfumeria.app.controller;
import com.perfumeria.app.model.Cliente;
import com.perfumeria.app.model.Venta;
import com.perfumeria.app.service.ClienteService;
import com.perfumeria.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired; // Permite la inyección automática de dependencias
import org.springframework.stereotype.Controller; // Define esta clase como un controlador MVC de Spring
import org.springframework.ui.Model; // Permite agregar atributos al modelo para pasarlos a la vista
import org.springframework.web.bind.annotation.GetMapping; // Importa todas las anotaciones para mapeo de solicitudes HTTP
import org.springframework.web.bind.annotation.RequestMapping; // Importa todas las anotaciones para mapeo de solicitudes HTTP
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String verReportes(Model model) {
        // 1. Obtener todos los datos necesarios
        List<Venta> ventas = ventaService.obtenerVentas();
        List<Cliente> clientes = clienteService.obtenerClientes();

        // 2. Crear un mapa de clientes por ID para una búsqueda eficiente
        Map<Integer, Cliente> clienteMap = clientes.stream()
                .collect(Collectors.toMap(Cliente::getId, cliente -> cliente));

        // 3. Asociar cada venta con su cliente correspondiente
        ventas.forEach(venta -> venta.setCliente(clienteMap.get(venta.getIdCliente())));

        // 4. Agregar las listas al modelo para la vista
        model.addAttribute("ventas", ventas);
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", "reportes"); // Para el menú de navegación
        return "reportes";
    }
}
