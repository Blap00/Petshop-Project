package com.littlepetshop.mvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.littlepetshop.mvc.models.Categoria;
import com.littlepetshop.mvc.models.Usuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
//		List<CategoriaProduct> categFindAll= categoriaRepository.findall(); //FALTA EL REPOSITORIO AUN
		boolean estaLogueado = (session.getAttribute("usuarioLogueado") != null);
        model.addAttribute("estaLogueado", estaLogueado);
		/*model.addAttribute("categorias", categFindAll);*/
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
