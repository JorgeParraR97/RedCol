package com.practica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practica.dto.SostenedorDTO;
import com.practica.service.ISostenedorService;

@Controller
@RequestMapping("admin")
public class AdminPageController {
	
	@Autowired
	ISostenedorService sosServicio;
	
	@GetMapping("sostenedor")
	public String proyecto(Model model) {
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST(); 
		model.addAttribute("sostenedor", sostenedor);
		return "/AdminPage/mantenedor";
	}
	
	
	@PostMapping("/rREST")
	public String saveREST(@Valid SostenedorDTO sostenedorDTO, Model model) {
	    try {
	        ResponseEntity<String> response = sosServicio.saveREST(sostenedorDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/sostenedor";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear admin");
	            // Redirige a la página de registro
	            return "redirect:/admin/sostenedor"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/sostenedor"; // Cambiar según la ruta real de tu página de registro
	    }
	}
}
