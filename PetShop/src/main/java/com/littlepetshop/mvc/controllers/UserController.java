package com.littlepetshop.mvc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.littlepetshop.mvc.models.Categoria;
import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.CategoryService;
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
	
	@Autowired 
	private final CategoryService categoryService;
	
	public UserController(UserService userService, UsuarioValidator uservalidator, CategoryService categoryService) {
		this.userService = userService;
		this.userValidator = uservalidator;
		this.categoryService = categoryService;
	}
	

	@GetMapping("/")
	public String index(Model model, HttpSession session, @ModelAttribute("usuario") Usuario usuario) {
	    boolean estaLogueado = (session.getAttribute("userId") != null);
	    List<Categoria> categorias = categoryService.findAll();	    
	    if (estaLogueado) {
	        Long userId = (Long) session.getAttribute("userId");
	        Optional<Usuario> user = userService.getUsuarioById(userId);
	        if (user.isPresent()) {
	            model.addAttribute("user", user.get());
	        }
	    }
	    
	    model.addAttribute("estaLogueado", estaLogueado);

	    model.addAttribute("categorias", categorias);
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
			if(u.isAdmin()) {
				session.setAttribute("staff", "true");
			}
			if(u.isSuperAdmin()) {
				session.setAttribute("superstaff", "true");
			}
			return "redirect:/";
		} else {
			session.setAttribute("error", "Credenciales invalidas");
			return "redirect:/login-in";
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
			
			return "register.jsp";
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
		session.removeAttribute("staff");
		session.removeAttribute("superstaff");
		
		return "redirect:/";
	}
}
