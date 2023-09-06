package com.littlepetshop.mvc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public AdminController(UserService userService, ProductService productService, BoletaService boletaService,
			DescuentoService descuentoService, CategoryService categoryService, SolicitudService solicitudService) {
		this.userService = userService;
		this.productService = productService;
		this.boletaService = boletaService;
		this.categoryService = categoryService;
		this.descuentoService = descuentoService;
		this.solicitudService = solicitudService;
	}

	@GetMapping("/")
	public String indexAdmin(HttpSession session, Model model) {
	    if ("true".equals(session.getAttribute("superstaff"))) {
	        return "redirect:/admin/dashboard";
	    } else if ("true".equals(session.getAttribute("staff"))) {
	        return "redirect:/admin/dashboard";
	    } else {
	        return "redirect:/";
	    }
	}


	@GetMapping("/showdata")
	public String showValues(@RequestParam String redirect, Model model, HttpSession session) {
		if (!"true".equals(session.getAttribute("staff"))) {
			return "redirect:/";
		}
		boolean estaLogueado = (session.getAttribute("userId") != null);
		if (estaLogueado) {
			Long userId = (Long) session.getAttribute("userId");
			Optional<Usuario> user = userService.getUsuarioById(userId);
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
			}
		}
		
		model.addAttribute("estaLogueado", estaLogueado);
		switch (redirect) {
		case "usuarios":
			if ("true".equals(session.getAttribute("superstaff"))) {
				model.addAttribute("list", userService.getAllUsuarios());
			}else {
				return "redirect/admin/";
			}
			break;
		case "boletas":
			model.addAttribute("list", boletaService.findAll());
			break;
		case "categorias":
			model.addAttribute("list", categoryService.findAll());
			break;
		case "descuento":
			model.addAttribute("list", descuentoService.getAllDescuentos());
			break;
		case "solicitudes":
			model.addAttribute("list", solicitudService.findAll());
			break;
		case "productos":
			model.addAttribute("list", productService.findAll());
			break;
		default :
			return "redirect:/admin/";
		}
		return "admin/showData.jsp";
	}

	@GetMapping("/dashboard")
	public String adminDashboard(Model model, HttpSession session) {
		boolean estaLogueado = (session.getAttribute("userId") != null);
		if (estaLogueado) {
			Long userId = (Long) session.getAttribute("userId");
			Optional<Usuario> user = userService.getUsuarioById(userId);
			if (user.isPresent()) {
				Usuario usuario=user.get();
				model.addAttribute("user", usuario);
				if(usuario.isSuperAdmin()) {
					session.setAttribute("superStaff", "true");
				}
			}
		}

		model.addAttribute("estaLogueado", estaLogueado);

		
		List<Boleta> boletas = boletaService.findAll();
		List<Categoria> categorias = categoryService.findAll();
		List<Descuento> descuentos = descuentoService.getAllDescuentos();
		List<Solicitud> solicitudes = solicitudService.findAll();
		List<Product> productos = productService.findAll();
		List<Usuario> admins = userService.getAllAdmins();
		// SET ON LIST
		if (session.getAttribute("superstaff") == "true") {
			
			model.addAttribute("superStaff", true);
			List<Usuario> users = userService.getAllUsuarios();
			model.addAttribute("users", users);
		}
		// GET LIST OF ADMINS;
		model.addAttribute("adminsList", admins);

		model.addAttribute("boletaList", boletas);
		model.addAttribute("categoriaList", categorias);
		model.addAttribute("descuentoList", descuentos);
		model.addAttribute("solicitudList", solicitudes);
		model.addAttribute("productoList", productos);

		return "admin/dashboard.jsp";
	}

}