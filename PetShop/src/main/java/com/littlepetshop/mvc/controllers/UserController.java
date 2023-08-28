package com.littlepetshop.mvc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String index(Model model, HttpSession session, @ModelAttribute("usuario") Usuario usuario) {
	    boolean estaLogueado = (session.getAttribute("userId") != null);
	    
	    if (estaLogueado) {
	        Long userId = (Long) session.getAttribute("userId");
	        Optional<Usuario> user = userService.getUsuarioById(userId);
	        if (user.isPresent()) {
	            model.addAttribute("user", user.get());
	        }
	    }
	    
	    model.addAttribute("estaLogueado", estaLogueado);
	    return "index.jsp";
	}
//	<-------INICIO DE SESION------->

	@GetMapping("/login")
	public String indexLogin() {
		return "login.jsp";
	}
	
	@PostMapping("/login/add")
	public String indexPost(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/login";
		}
		else {
			return "redirect:/";
		}
	}
//  <-------REGISTRO DE USUARIO------->
	@GetMapping("/register")
	public String indexRegister() {
		return "register.jsp";
	}
	@PostMapping("/register/add")
	public String indexRegPost(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/register";
		}
		else {
			return "redirect:/login";	
		}
	}
}
