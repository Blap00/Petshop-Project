package com.littlepetshop.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        Integer totalUsuarios = userService.getTotalUsuarios();
        model.addAttribute("totalUsuarios", totalUsuarios);
        return "admin/dashboard";
    }

    @GetMapping("/admin/manage-users")
    public String manageUsers(Model model) {
        List<Usuario> admins = userService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/manage-users";
    }

    @GetMapping("/admin/manage-admins")
    public String manageAdmins(Model model) {
        List<Usuario> admins = userService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/manage-admins";
    }

    @GetMapping("/admin/manage-products")
    public String manageProducts(Model model) {
        // Implementar la lógica para que el admin pueda gestionar productos
        // utilizar el model para pasar información a la vista
        // métodos correspondientes en tus servicios y repositorios
        return "admin/manage-products";
    }
//tambien faltaria cambiar el nombre de las rutas
}
