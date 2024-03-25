package com.practica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practica.dto.LoginDTO;

@Controller
@RequestMapping("sostenedor")
public class SostenedorPageController {
	
	@GetMapping("/home")
	public String showLoginForm(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "/SostenedorPage/prueba";
	}
	


}
