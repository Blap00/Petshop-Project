package com.littlepetshop.mvc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.littlepetshop.mvc.models.Categoria;
import com.littlepetshop.mvc.models.Descuento;
import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.CategoryService;
import com.littlepetshop.mvc.services.DescuentoService;
import com.littlepetshop.mvc.services.ProductService;
import com.littlepetshop.mvc.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CatProductController {

	@Autowired
	private final CategoryService categoryServ;

	@Autowired
	private final DescuentoService descuentoServ;

	@Autowired
	private final ProductService productServ;

	@Autowired
	private final UserService userService;

	public CatProductController(DescuentoService descuentoServ, CategoryService categoryServ,
			ProductService productServ, UserService userService) {
		this.categoryServ = categoryServ;
		this.descuentoServ = descuentoServ;
		this.productServ = productServ;
		this.userService = userService;
	}

	@GetMapping("/catalogo")
	public String showCatalogo(Model model, HttpSession session) {
		boolean estaLogueado = (session.getAttribute("userId") != null);

		if (estaLogueado) {
			Long userId = (Long) session.getAttribute("userId");
			Optional<Usuario> user = userService.getUsuarioById(userId);
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
			}
		}

		model.addAttribute("estaLogueado", estaLogueado);

		// Retrieve products, catalogos, and descuentos using services
		List<Product> products = productServ.findAll();
		Iterable<Categoria> catalogos = categoryServ.findAll();
		Iterable<Descuento> descuentos = descuentoServ.getAllDescuentos();

		// Add retrieved data to the model
		model.addAttribute("cart",session.getAttribute("cart"));
		model.addAttribute("products", products);
		model.addAttribute("catalogos", catalogos);
		model.addAttribute("descuentos", descuentos);

		return "productShow.jsp"; // Return the appropriate view name
	}

	@GetMapping("/category/{id}")
	public String index(@PathVariable Long id, Model model, HttpSession session) {
		boolean estaLogueado = (session.getAttribute("userId") != null);    
		if (estaLogueado) {
			Long userId = (Long) session.getAttribute("userId");
			Optional<Usuario> user = userService.getUsuarioById(userId);
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
			}
		}

		model.addAttribute("estaLogueado", estaLogueado);
		
		Iterable<Product> products = productServ.findByCategoriaId(id);
		
		Iterable<Categoria> catalogos = categoryServ.findAll();
		Iterable<Descuento> descuentos = descuentoServ.getAllDescuentos();
		// Add retrieved data to the model
		model.addAttribute("cart",session.getAttribute("cart"));
		model.addAttribute("categoriaActual", id);
		model.addAttribute("products", products);
		model.addAttribute("catalogos", catalogos);
		model.addAttribute("descuentos", descuentos);
		return "categoryID.jsp";
	}

}
