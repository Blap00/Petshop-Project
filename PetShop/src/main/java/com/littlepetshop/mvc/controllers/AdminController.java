package com.littlepetshop.mvc.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.littlepetshop.mvc.models.Boleta;
import com.littlepetshop.mvc.models.Categoria;
import com.littlepetshop.mvc.models.Descuento;
import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.models.Solicitud;
import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.BoletaService;
import com.littlepetshop.mvc.services.CategoryService;
import com.littlepetshop.mvc.services.DescuentoService;
import com.littlepetshop.mvc.services.ProductService;
import com.littlepetshop.mvc.services.SolicitudService;
import com.littlepetshop.mvc.services.UserService;
import com.littlepetshop.mvc.validators.SuperAdminValidator;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private final UserService userService;
	@Autowired
	private final ProductService productService;
	@Autowired
	private final BoletaService boletaService;
	@Autowired
	private final CategoryService categoryService;
	@Autowired
	private final DescuentoService descuentoService;
	@Autowired
	private final SolicitudService solicitudService;

	@Autowired
	public AdminController(UserService userService, ProductService productService, BoletaService boletaService, DescuentoService descuentoService, CategoryService categoryService, SolicitudService solicitudService) {
		this.userService = userService;
		this.productService = productService;
		this.boletaService = boletaService;
		this.categoryService = categoryService;
		this.descuentoService = descuentoService;
		this.solicitudService = solicitudService;
	}
	
	
	@GetMapping("/")
	public String indexAdmin(HttpSession session, Model model) {
		if(session.getAttribute("superstaff")=="true") {
			return "redirect:/admin/dashboard";
		}else if(session.getAttribute("superstaff")!="true" && session.getAttribute("staff")=="true") {
			return "redirect:/admin/dashboard";
		}else {
			return "redirect:/";
		}
	}

	@GetMapping("/manage-something")
	public String manageSomething(Model model, Principal principal) {
		// Verificar si el usuario actual es "superadmin" ya ha sido realizado por el
		// validador
		// Implementa la lógica para gestionar "algo" aquí
		return "admin/manage-something.jsp";
	}

	

	@GetMapping("/dashboard")
	public String adminDashboard(Model model,HttpSession session) {
	    boolean estaLogueado = (session.getAttribute("userId") != null);
	    if (estaLogueado) {
	        Long userId = (Long) session.getAttribute("userId");
	        Optional<Usuario> user = userService.getUsuarioById(userId);
	        if (user.isPresent()) {
	            model.addAttribute("user", user.get());
	        }
	    }
	    
	    model.addAttribute("estaLogueado", estaLogueado);
	    
		if(session.getAttribute("superstaff")=="true") {
			model.addAttribute("superStaff", true);
			List<Usuario> users = userService.getAllUsuarios();
			model.addAttribute("users", users);
		}
		List<Boleta> boletas = boletaService.findAll();
		List<Categoria> categorias = categoryService.findAll();
		List<Descuento> descuentos = descuentoService.getAllDescuentos();
		List<Solicitud> solicitudes = solicitudService.findAll(); 
		List<Product> productos = productService.findAll();
		List<Usuario> admins = userService.getAllAdmins();
		//SET ON LIST
		
		//GET LIST OF ADMINS;
		model.addAttribute("adminsList", admins);
		
		model.addAttribute("boletaList", boletas);
		model.addAttribute("categoriaList", categorias);
		model.addAttribute("descuentoList", descuentos);
		model.addAttribute("solicitudList", solicitudes);
		model.addAttribute("productoList", productos);
		
		return "admin/dashboard.jsp";
	}

	@GetMapping("/manage-users")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public String manageUsers(Model model) {
		List<Usuario> admins = userService.getAllAdmins();
		model.addAttribute("admins", admins);
		return "admin/manage-users";
	}

	// para permitir que solo el super admin acceda al método manageAdmins
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