package com.littlepetshop.mvc.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.littlepetshop.mvc.models.Usuario;
import com.littlepetshop.mvc.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userService ;
	
	public UserController(UserService userService) {
		this.userService = userService;	
	}
	
//	@GetMapping("/")
//	public String index(Model model, HttpSession session) {
////		List<CategoriaProduct> categFindAll= categoriaRepository.findall(); //FALTA EL REPOSITORIO AUN
//		boolean estaLogueado = ((Usuario) session.getAttribute("userId") != null);
//		Optional<Usuario> user = (Optional<Usuario>)session.getAttribute("userId");
//		if(user.isPresent()) {
//			user.get();
//		}
//		model.addAttribute("user", user);
//        model.addAttribute("estaLogueado", estaLogueado);
////		model.addAttribute("categorias", categFindAll);
//		return "index.jsp";
//	}
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
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
//	<-------------LOGOUT--------------->
	@GetMapping("/logout-in")
	public String redirectIndex(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
}
