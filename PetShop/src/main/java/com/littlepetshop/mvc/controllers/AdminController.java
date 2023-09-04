package com.littlepetshop.mvc.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.ProductService;
import com.littlepetshop.mvc.services.UserService;
import com.littlepetshop.mvc.validators.SuperAdminValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final UserService userService;
    private final ProductService productService;


    @Autowired
    private SuperAdminValidator superAdminValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(superAdminValidator);
    }

    @GetMapping("/manage-something")
    public String manageSomething(Model model, Principal principal) {
        // Verificar si el usuario actual es "superadmin" ya ha sido realizado por el validador
        // Implementa la lógica para gestionar "algo" aquí
        return "admin/manage-something";
    }
    
    
    
    
    @Autowired
    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }
 
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        Integer totalUsuarios = userService.getTotalUsuarios();
        model.addAttribute("totalUsuarios", totalUsuarios);
        return "admin/dashboard";
    }

    @GetMapping("/manage-users")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String manageUsers(Model model) {
        List<Usuario> admins = userService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/manage-users";
    }

    //para permitir que solo el super admin acceda al método manageAdmins
    @GetMapping("/manage-admins")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public String manageAdmins(Model model) {
        List<Usuario> admins = userService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/manage-admins";
    }

    @GetMapping("/manage-products")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String manageProducts(Model model) {
        List<Product> productos = productService.findAll();
        model.addAttribute("productos", productos);
        return "admin/manage-products";
    }
}