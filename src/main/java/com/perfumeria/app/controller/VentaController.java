package com.perfumeria.app.controller;

import com.perfumeria.app.dto.ProductoDTO;
import com.perfumeria.app.dto.SubVentaDTO;
import com.perfumeria.app.dto.VentaDTO;
import com.perfumeria.app.service.ProductoService;
import com.perfumeria.app.service.SubVentaService;
import com.perfumeria.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private SubVentaService subVentaService;

    @GetMapping
    public String mostrarVentas(Model model, @RequestParam(required = false) String busqueda) {
        model.addAttribute("ventaDTO", new VentaDTO()); // Crea un objeto VentaDTO para crear una nueva venta
        model.addAttribute("paginaActual", "ventas"); // Indica la página actual para marcar el menú activo
        
        Integer ultimaVentaId = ventaService.obtenerUltimaVenta(); // Obtiene el ID de la última venta
        model.addAttribute("ultimaVentaId", ultimaVentaId); // Agrega el ID de la última venta al modelo
        
        SubVentaDTO subVentaDTO = new SubVentaDTO(); // Crea un objeto SubVentaDTO para agregar productos a la venta

        List<SubVentaDTO> subVentas = null; // Inicializa la lista de subventas vacio

        if (ultimaVentaId != null) {
            subVentaDTO.setIdVenta(ultimaVentaId); // Asocia el SubVentaDTO con la última venta
            model.addAttribute("subVentaDTO", subVentaDTO); // Agrega el SubVentaDTO al modelo
            subVentas = subVentaService.obtenerSubVentasPorVenta(ultimaVentaId); // Obtiene las subventas asociadas a la última venta
            model.addAttribute("subVentas", subVentas); // Agrega las subventas al modelo
        }

        // Calcula el total sumando los submontos de las subventas
        Double total = 0.0;
        if (subVentas != null && !subVentas.isEmpty()) {
            total = subVentas.stream()
                    .map(SubVentaDTO::getSubtotal)
                    .filter(subtotal -> subtotal != null)
                    .mapToDouble(Double::doubleValue)
                    .sum();
        }
        model.addAttribute("totalVenta", total); // Agrega el total de la venta al modelo Venta
        
        // Busca el producto
        List<ProductoDTO> productos;
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            productos = productoService.buscarProductos(busqueda); // Busca productos según el término de búsqueda
            model.addAttribute("terminoBusqueda", busqueda); // Agrega el término de búsqueda al modelo
        } else {
            productos = productoService.listarProductos(); // Lista todos los productos si no hay término de búsqueda
        }
        
        model.addAttribute("productos", productos); // Agrega la lista de productos al modelo
        return "ventas";
    }

    @PostMapping
    public String guardarVenta(@ModelAttribute VentaDTO ventaDTO, RedirectAttributes redirectAttributes) {
        try {
            ventaService.guardarVenta(ventaDTO); // Guarda la venta utilizando el servicio VentaService
            redirectAttributes.addFlashAttribute("mensaje", "Venta creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear la venta: " + e.getMessage());
        }
        return "redirect:/ventas";
    }
    
    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam String termino, Model model) {
        return "redirect:/ventas?busqueda=" + termino; // Redirige a la página de ventas con el término de búsqueda
    }
    
    @PostMapping("/subventa")
    public String guardarSubVenta(@ModelAttribute SubVentaDTO subVentaDTO, RedirectAttributes redirectAttributes) {
        try {
            subVentaService.guardarSubVenta(subVentaDTO); // Guarda la subventa utilizando el servicio SubVentaService
            redirectAttributes.addFlashAttribute("mensaje", "Producto agregado a la venta exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al agregar producto: " + e.getMessage());
        }
        return "redirect:/ventas";
    }
}