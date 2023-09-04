package com.littlepetshop.mvc.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.littlepetshop.mvc.models.Product;
import com.littlepetshop.mvc.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CarritoController {
    
	@Autowired
	private final ProductService productService;
	@Autowired
	private ScheduledExecutorService scheduler;
	
	private final Map<Long, Boolean> purchasedProducts = new ConcurrentHashMap<>();

	public CarritoController(ProductService productService, ScheduledExecutorService scheduler) {
		this.productService = productService;
		this.scheduler = Executors.newScheduledThreadPool(1);
	}
	
//	<--------------------CARRITO-------------------->
	@GetMapping("/")
    public String viewCart(Model model, HttpSession session) {
        // Retrieve the shopping cart from the session as an Optional
        Optional<Object> cartOptional = Optional.ofNullable(session.getAttribute("cart"));
        //GET METHO
        // If the cart is present and of the correct type, use it; otherwise, create a new empty cart
        List<Product> cart = cartOptional.filter(List.class::isInstance).map(List.class::cast).orElseGet(ArrayList::new);
        
        model.addAttribute("cart", cart);
        return "cart.jsp";
    }
//	<--------------------AÑADIR AL CARRO-------------------->
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer stock, HttpSession session, @RequestParam String redirect) {

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
            Optional<Object> cartOptional = Optional.ofNullable(session.getAttribute("cart"));

            List<Product> cart = cartOptional.filter(List.class::isInstance).map(List.class::cast).orElseGet(ArrayList::new);

            cart.add(product);

            session.setAttribute("cart", cart);
        }
        return "redirect:/" + redirect;
    }
//	<--------------------Compra del carrito-------------------->
    
    @PostMapping("/purchase")
    public String purchaseCart(HttpSession session) {
        // Realiza la lógica de compra aquí

        // Marca los productos como comprados
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart != null) {
            for (Product product : cart) {
                purchasedProducts.put(product.getId(), true);
            }
        }

        // Limpia el carrito después de la compra
        session.removeAttribute("cart");

        return "redirect:/"; // Redirige a donde sea apropiado después de la compra
    }
}