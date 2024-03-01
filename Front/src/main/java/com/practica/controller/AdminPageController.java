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

import com.practica.dto.EstablecimientoDTO;
import com.practica.dto.PagosDTO;
import com.practica.dto.SostenedorDTO;
import com.practica.service.IEstablecimientoService;
import com.practica.service.IPagosService;
import com.practica.service.ISostenedorService;

@Controller
@RequestMapping("admin")
public class AdminPageController {
	
	@Autowired
	ISostenedorService sosServicio;
	
	@Autowired
	IEstablecimientoService estServicio;
	
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
	
	@GetMapping("/esREST/{id}")
	@ResponseBody // Indica que el valor devuelto debe ser serializado como respuesta HTTP
	public ResponseEntity<List<EstablecimientoDTO>> getEstablecimientos(@PathVariable("id") int id) {
	    try {
	        List<EstablecimientoDTO> establecimientos = sosServicio.getEstablecimientos(id);
	        return new ResponseEntity<>(establecimientos, HttpStatus.OK);
	    } catch (Exception e) {
	        // Manejar errores
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("establecimiento")
	public String colegio(Model model) {
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();
		
		model.addAttribute("sostenedor", sostenedor);
		model.addAttribute("establecimiento", establecimiento);
		return "/AdminPage/mantenedor_establecimiento";
	}
	
	
	@PostMapping("/geREST")
	public String saveREST(@Valid EstablecimientoDTO establecimientoDTO, Model model) {
	    try {
	        ResponseEntity<String> response = estServicio.saveREST(establecimientoDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/establecimiento";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear establecimiento");
	            // Redirige a la página de registro
	            return "redirect:/admin/establecimiento"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/establecimiento"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	@PostMapping("/aeREST")
    public String actualizarEstablecimiento(@Valid EstablecimientoDTO establecimientoDTO, Model model) {
        try {
        	estServicio.actualizarEstablecimiento(establecimientoDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/establecimiento";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar establecimiento");
            return "redirect:/admin/establecimiento";
        }
    }
	
	
	@DeleteMapping("/beREST/{id}")
	public ResponseEntity<String> deleteEstablecimiento(@PathVariable int id) {
	    try {
	        estServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	@GetMapping("pagos")
	public String pagos(Model model) {
		List<PagosDTO> pagos = pagServicio.findAllREST(); 
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();
		
		model.addAttribute("pagos", pagos);
		model.addAttribute("establecimiento", establecimiento);
		model.addAttribute("sostenedor", sostenedor);
		return "/AdminPage/mantenedor_pagos";
	}
	
	
    @GetMapping("/findSos")
    public List<SostenedorDTO> findAllSos() {
        return sosServicio.findAllREST();
    }
    
    @GetMapping("/findEst")
    public List<EstablecimientoDTO> findAllCol() {
        return estServicio.findAllREST();
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
