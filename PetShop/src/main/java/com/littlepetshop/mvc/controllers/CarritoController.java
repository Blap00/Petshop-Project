package com.littlepetshop.mvc.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.littlepetshop.mvc.models.Boleta;
import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.BoletaService;
import com.littlepetshop.mvc.services.ProductService;
import com.littlepetshop.mvc.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CarritoController {
	@Autowired
	private final UserService userService;
	@Autowired
	private final ProductService productService;
	@Autowired
	private ScheduledExecutorService scheduler;
	@Autowired
	private final BoletaService boletaService;

	private final Map<Long, Boolean> purchasedProducts = new ConcurrentHashMap<>();
	private boolean islogged;

	public CarritoController(ProductService productService, ScheduledExecutorService scheduler, UserService userService,
			BoletaService boletaService) {
		this.userService = userService;
		this.productService = productService;
		this.scheduler = Executors.newScheduledThreadPool(1);
		this.boletaService = boletaService;
	}

//	<--------------------CARRITO-------------------->
	@GetMapping("/")
	public String viewCart(Model model, HttpSession session, @ModelAttribute("boleta") Boleta boleta) {
		boolean estaLogueado = (session.getAttribute("userId") != null);

		if (estaLogueado) {
			Long userId = (Long) session.getAttribute("userId");
			Optional<Usuario> user = userService.getUsuarioById(userId);
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
				model.addAttribute("userid", session.getAttribute("userId"));
				// Establece el valor de usuario solo si está autenticado
				model.addAttribute("usuario", userId);
			}
		}
		model.addAttribute("estaLogueado", estaLogueado);
		model.addAttribute("cart", session.getAttribute("cart"));
		return "cart/cart.jsp";
	}

//	<--------------------AÑADIR AL CARRO-------------------->
	@PostMapping("/add")
	public String addToCart(@RequestParam Long productId, @RequestParam Integer stock, HttpSession session,
			@RequestParam String redirect) {
		Product product = productService.findById(productId);

		if (productService.getStockByID(productId) >= 0) {
			productService.removeStockById(productId, stock);
			scheduler.schedule(() -> {
				if (!purchasedProducts.getOrDefault(productId, false)) {
					Product revertedProduct = productService.findById(productId);
					if (revertedProduct != null) {
						revertedProduct.setStock(revertedProduct.getStock() + stock);
						productService.save(revertedProduct);
						session.setAttribute("cart", null);
					}
				}
			}, 30, TimeUnit.MINUTES);
		}
		if (product != null) {
			// Verifica el Stock
			if (stock >= 1 && stock <= product.getStock()) {
				List<Product> cart = (List<Product>) session.getAttribute("cart");

				// Si el carrito aún no existe en la sesión, este se crea
				if (cart == null) {
					cart = new ArrayList<>();
				}

				// Agrega el producto al carrito con la cantidad deseada
				Product cartProduct = new Product();
				cartProduct.setId(product.getId());
				cartProduct.setName(product.getName());
				cartProduct.setPrice(product.getPrice());
				cartProduct.setImagenes(product.getImagenes());
				cartProduct.setStock(stock);

				cart.add(cartProduct);
				session.setAttribute("cart", cart);

			}
		}

		return "redirect:/" + redirect;
	}
//	<--------------------Compra del carrito-------------------->

	@PostMapping("/purchase")
	public String purchaseCart(HttpSession session, @ModelAttribute("boleta") Boleta boleta, BindingResult result,
			Model model) {
		Usuario usuario = null;
		if (result.hasErrors()) {
			return "redirect:/cart/";
		} else {

			Long userId = (Long) session.getAttribute("userId");

			if (userId != null) {

				Optional<Usuario> userOptional = userService.getUsuarioById(userId);

				if (userOptional.isPresent()) {
					usuario = userOptional.get();
					islogged=true;
				}
			} else {
				Optional<Usuario> optuser = userService.getUsuarioById((long) 81);
				if (optuser.isPresent()) {
					usuario = optuser.get();
					islogged=false;

				}
			}

			List<Product> cart = (List<Product>) session.getAttribute("cart");
			if (cart != null) {
				for (Product product : cart) {
		            Boleta newBoleta = new Boleta();
		            newBoleta.setPriceProduct(product.getStock() * product.getPrice());
		            newBoleta.setCatalogo(product);
		            newBoleta.setUsuario(usuario);

		            // Guarda la boleta en la base de datos
		            boletaService.save(newBoleta);
		        }
			}

			// Limpia el carrito después de la compra
			session.removeAttribute("cart");

			return "redirect:/"; // Redirige a donde corresponda en caso de error
		}
	}

//  <----------------DELETE OWN SHOPPING-CART ITEM-------------------->
	@GetMapping("/delete/{productId}")
	public String removeFromCart(@PathVariable Long productId, HttpSession session) {
		List<Product> cart = (List<Product>) session.getAttribute("cart");

		if (cart != null) {
			// Busca el producto en el carrito por su ID
			Iterator<Product> iterator = cart.iterator();
			while (iterator.hasNext()) {
				Product product = iterator.next();
				if (product.getId().equals(productId)) {
					// Restituye el stock del producto eliminado
					productService.addStockById(productId, product.getStock());
					// Elimina el producto del carrito
					iterator.remove();
					break;
				}
			}
			session.setAttribute("cart", cart);
		}

		return "redirect:/cart/"; // Redirige de vuelta al carrito
	}
}