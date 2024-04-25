package com.microservice.bff.Contoller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.microservice.bff.DTO.AdminDTO;
import com.microservice.bff.DTO.LoginDTO;
import com.microservice.bff.Service.IAdminService;



@RestController
@RequestMapping("/api/bff/admin")
public class BffAdminController {
	
    private final Logger logger = LoggerFactory.getLogger(BffAdminController.class);


	@Autowired
	IAdminService adminService;
	

	@GetMapping("/findAll")
    public List<AdminDTO> AdminFindAll(){
		return adminService.AdminFindAll(); 
    }
	
	@PostMapping("/create")
	public ResponseEntity<String> AdminSave(@RequestBody @Valid AdminDTO admin) {
	    try {
	        if (admin == null) {
	            logger.error("Datos de admin no válidos. Rechazando la solicitud. Datos: {}", admin);
	            return ResponseEntity.badRequest().body("Datos de admin no válidos");
	        }

	        logger.info("Recibida solicitud para crear admin. Datos: {}", admin);

	        adminService.AdminSave(admin);

	        logger.info("Admin creado exitosamente. Datos: {}", admin);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Admin creado exitosamente");
	    } catch (Exception e) {
	        logger.error("Error interno al procesar la solicitud de creación de admin. Datos: {}", admin, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}

	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> adminLogin(@RequestBody LoginDTO loginDTO) {
	    try {
	        ResponseEntity<LoginDTO> isValidCredentials = adminService.AdminLogin(loginDTO);

	        if (isValidCredentials != null) {
	            return ResponseEntity.ok(isValidCredentials.getBody());
	        } else {
	            return ResponseEntity.status(401).body(new LoginDTO()); // Puedes ajustar esto según tu necesidad
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body(new LoginDTO()); // Puedes ajustar esto según tu necesidad
	    }
	}
}
	
