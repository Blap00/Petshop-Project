package com.littlepetshop.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.littlepetshop.mvc.models.Usuario;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String index() {
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
