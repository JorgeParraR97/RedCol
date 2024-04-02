package com.practica.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.practica.dto.AdminDTO;
import com.practica.dto.LoginDTO;
import com.practica.dto.SostenedorDTO;
import com.practica.service.IAdminService;
import com.practica.service.ISostenedorService;

@Controller
@RequestMapping("login")
public class WebPageController {

	@Autowired
	private IAdminService servicio;
	
	@Autowired
	ISostenedorService sosServicio;
	
	

	// http://localhost:8081/persona/listar/REST
	@GetMapping("/admin")
	public String showLoginForm(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "/WebPage/login";
	}

	@PostMapping("/isREST")
	public String loginREST(@Valid LoginDTO loginDTO, Model model) {
	    // Llamada al servicio para realizar la autenticación
	    LoginDTO responseDTO = servicio.loginREST(loginDTO);

	    if (responseDTO != null) {
	        // Autenticación exitosa
	        return "redirect:/admin/sostenedor";
	    } else {
	        // Autenticación fallida, agregar mensaje de error al modelo
	        model.addAttribute("error", "Credenciales inválidas");
	        return "redirect:/login/admin"; // Cambiar según la ruta real de tu página de login
	    }
	}

	// http://localhost:8081/persona/listar/REST
	@GetMapping("/registro")
	public String mostrarPaginaRegistro(Model model) {
	    model.addAttribute("adminDTO", new AdminDTO());
	    return "/WebPage/register";
	}
	
	
	
	@PostMapping("/rREST")
	public String saveREST(@Valid AdminDTO adminDTO, Model model) {
	    try {
	        ResponseEntity<String> response = servicio.saveREST(adminDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/login/admin";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear admin");
	            // Redirige a la página de registro
	            return "redirect:/login/registro"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/login/registro"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	@PostMapping("/issREST")
	public String loginsosREST(@Valid LoginDTO loginDTO, Model model, HttpSession session) {
	    // Obtener la lista de SostenedorDTO
	    List<SostenedorDTO> sostenedores = sosServicio.findAllREST();

	    // Buscar en la lista de sostenedores el que coincida con el email y contraseña proporcionados
	    Long sostenedorId = null;
	    for (SostenedorDTO sostenedor : sostenedores) {
	        if (sostenedor.getEmail().equals(loginDTO.getEmail()) && sostenedor.getContrasena().equals(loginDTO.getContrasena())) {
	        	sostenedorId = Long.valueOf(sostenedor.getId());
	            break;
	        }
	    }

	    if (sostenedorId != null) {
	        // Si se encontró un sostenedor con las credenciales proporcionadas, almacenar su ID en la sesión
	        session.setAttribute("sostenedorId", sostenedorId);
	        // Autenticación exitosa
	        return "redirect:/sostenedor/home";
	    } else {
	        // Autenticación fallida, agregar mensaje de error al modelo
	        model.addAttribute("error", "Credenciales inválidas");
	        // Cargar el modelo con la lista de sostenedores (para mostrar en caso de error)
	        model.addAttribute("sostenedor", sostenedores);
	        return "redirect:/login/admin"; // Cambiar según la ruta real de tu página de login
	    }
	}
	
	

	
	
	
	
	

}
