package com.practica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practica.dto.ColegioDTO;
import com.practica.dto.PagosDTO;
import com.practica.dto.SostenedorDTO;
import com.practica.service.IColegioService;
import com.practica.service.IPagosService;
import com.practica.service.ISostenedorService;

@Controller
@RequestMapping("admin")
public class AdminPageController {
	
	@Autowired
	ISostenedorService sosServicio;
	
	@Autowired
	IColegioService colServicio;
	
	@Autowired
	IPagosService pagServicio;
	
	@GetMapping("sostenedor")
	public String sostenedor(Model model) {
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST(); 
		model.addAttribute("sostenedor", sostenedor);
		return "/AdminPage/mantenedor_sostenedor";
	}
	
	
	@PostMapping("/gsREST")
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
	
	@PostMapping("/asREST")
    public String actualizarSostenedor(@Valid SostenedorDTO sostenedorDTO, Model model) {
        try {
        	sosServicio.actualizarSostenedor(sostenedorDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/sostenedor";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar sostenedor");
            return "redirect:/admin/sostenedor";
        }
    }
	
	@DeleteMapping("/bsREST/{id}")
	public ResponseEntity<String> deleteSostenedor(@PathVariable int id) {
	    try {
	        sosServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	@GetMapping("/csREST/{id}")
	@ResponseBody // Indica que el valor devuelto debe ser serializado como respuesta HTTP
	public ResponseEntity<List<ColegioDTO>> getColegios(@PathVariable("id") int id) {
	    try {
	        List<ColegioDTO> colegios = sosServicio.getColegios(id);
	        return new ResponseEntity<>(colegios, HttpStatus.OK);
	    } catch (Exception e) {
	        // Manejar errores
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("colegio")
	public String colegio(Model model) {
		List<ColegioDTO> colegio = colServicio.findAllREST(); 
		model.addAttribute("colegio", colegio);
		return "/AdminPage/mantenedor_colegios";
	}
	
	
	@PostMapping("/gcREST")
	public String saveREST(@Valid ColegioDTO colegioDTO, Model model) {
	    try {
	        ResponseEntity<String> response = colServicio.saveREST(colegioDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/colegio";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear colegio");
	            // Redirige a la página de registro
	            return "redirect:/admin/colegio"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/colegio"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	@PostMapping("/acREST")
    public String actualizarColegio(@Valid ColegioDTO colegioDTO, Model model) {
        try {
        	colServicio.actualizarColegio(colegioDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/colegio";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar colegio");
            return "redirect:/admin/colegio";
        }
    }
	
	
	@DeleteMapping("/bcREST/{id}")
	public ResponseEntity<String> deleteColegio(@PathVariable int id) {
	    try {
	        colServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	@GetMapping("pagos")
	public String pagos(Model model) {
		List<PagosDTO> pagos = pagServicio.findAllREST(); 
		List<ColegioDTO> colegio = colServicio.findAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();
		model.addAttribute("pagos", pagos);
		model.addAttribute("colegio", colegio);
		model.addAttribute("sostenedor", sostenedor);
		return "/AdminPage/mantenedor_pagos";
	}
	
	
    @GetMapping("/findSos")
    public List<SostenedorDTO> findAllSos() {
        return sosServicio.findAllREST();
    }
    
    @GetMapping("/findCol")
    public List<ColegioDTO> findAllCol() {
        return colServicio.findAllREST();
    }
    
    @PostMapping("/gpREST")
	public String saveREST(@Valid @ModelAttribute PagosDTO pagosDTO, Model model) {
	    try {
	        ResponseEntity<String> response = pagServicio.saveREST(pagosDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/pagos";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear pagos");
	            // Redirige a la página de registro
	            return "redirect:/admin/pagos"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/pagos"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	
	
}
