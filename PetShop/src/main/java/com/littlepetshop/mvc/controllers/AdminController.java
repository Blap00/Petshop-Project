package com.littlepetshop.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.ProductService;
import com.littlepetshop.mvc.services.UserService;

@Controller   
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;

    public AdminController(UserService userService) {
		super();
		this.userService = userService;
	}
 
    
	public AdminController(ProductService productService) {
		super();
		this.productService = productService;
	}


	@GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        Integer totalUsuarios = userService.getTotalUsuarios();
        model.addAttribute("totalUsuarios", totalUsuarios);
        return "admin/dashboard";
    }

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        List<Usuario> admins = userService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/manage-users";
    }

    @GetMapping("/manage-admins")
    public String manageAdmins(Model model) {
        List<Usuario> admins = userService.getAllAdmins();
        model.addAttribute("admins", admins);
        return "admin/manage-admins";
    }

    @GetMapping("/manage-products")
    public String manageProducts(Model model) {
        List<Product> productos = productService.findAll();
        model.addAttribute("productos", productos);
        return "admin/manage-products";
    }
//tambien faltaria cambiar el nombre de las rutas
}
