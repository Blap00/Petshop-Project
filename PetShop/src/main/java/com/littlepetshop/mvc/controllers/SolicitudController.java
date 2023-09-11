package com.littlepetshop.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.littlepetshop.mvc.models.Solicitud;
import com.littlepetshop.mvc.services.SolicitudService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/solicitudes") // Ruta base para las solicitudes
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    // ... Otros atributos y servicios necesarios

    // Controlador para mostrar el formulario de creación de solicitudes
    @GetMapping("/nueva")
    public String mostrarFormularioSolicitud(Model model, @ModelAttribute("solicitud")Solicitud solicitud) {
        return "solicitud.jsp"; // Retornar la vista del formulario de solicitud
    }

 // Controlador para procesar el formulario de creación de solicitudes
    @PostMapping("/crear")
    public String crearSolicitud(@ModelAttribute("solicitud") Solicitud solicitud, BindingResult result, Model model, HttpSession session) {
        // Verificar si el usuario está autenticado
        boolean estaLogueado = (session.getAttribute("userId") != null);
        if (!estaLogueado) {
            return "redirect:/"; // Redirigir al inicio si el usuario no está autenticado
        }

        // Realizar la lógica para guardar la solicitud en la base de datos
        if (result.hasErrors()) {
            // Manejar errores de validación si es necesario
            return "solicitud.jsp"; // Retornar nuevamente el formulario en caso de errores
        } else {
            // Guardar la solicitud en la base de datos

            Solicitud nuevaSolicitud = solicitudService.guardarSolicitud(solicitud);

            // Redirigir a una página de confirmación
            // Puedes pasar datos adicionales a la página de confirmación si es necesario
            model.addAttribute("solicitud", nuevaSolicitud);

            return "redirect:/solicitudes/confirmacion"; // Redirigir a una página de confirmación
        }
    }

    // Controlador para mostrar una página de confirmación (puedes crear esta vista)
    @GetMapping("/confirmacion")
    public String mostrarConfirmacion() {
        return "confirmacion.jsp"; // Retornar la vista de confirmación
    }
}


