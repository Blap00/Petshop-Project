package com.littlepetshop.mvc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.UserService;
import com.littlepetshop.mvc.services.UserService;
import com.littlepetshop.mvc.validators.UsuarioValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	

	@Autowired
	private final UserService userService;
	
	@Autowired
	private final UsuarioValidator userValidator;
	
	public UserController(UserService userService, UsuarioValidator uservalidator) {
		this.userService = userService;
		this.userValidator = uservalidator;
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

	@GetMapping("/login-in")
	public String indexLogin(@ModelAttribute("usuario") Usuario usuario) {
		return "login.jsp";
	}
	
	@PostMapping("/login/add")
	public String indexPost(@RequestParam("username") String username, @RequestParam("password") String password, Model model,
			HttpSession session) {
		boolean isAuntentico = userService.authenticateUser(username, password);
		if (isAuntentico) {
			Usuario u = userService.findByUsername(username);
			session.setAttribute("userId", u.getId());
			return "redirect:/";
		} else {
			session.setAttribute("error", "Clave o email invalida");
			return "redirect:/login";
		}
	}
//  <-------REGISTRO DE USUARIO------->
	@GetMapping("/register-in")
	public String indexRegister(@ModelAttribute("usuario") Usuario usuario) {
		return "register.jsp";
	}
	
	@PostMapping("/register/add")
	public String indexRegPost(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult result, HttpSession session) {
		userValidator.validate(usuario, result);
		if(result.hasErrors()) {
			return "redirect:/register";
		}
		else {
			Usuario u = userService.registerUser(usuario);
			session.setAttribute("userId", u.getId());
			return "redirect:/login-in";	
		}
	}
	
//	<-------------LOGOUT--------------->
	@GetMapping("/logout-in")
	public String redirectIndex(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
}
