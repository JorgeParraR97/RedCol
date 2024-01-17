package com.practica.AdminRC.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practica.AdminRC.dto.AdminDTO;
import com.practica.AdminRC.dto.LoginDTO;
import com.practica.AdminRC.service.IAdminService;


@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	private IAdminService servicio;
	
	
	
	@ResponseBody @PostMapping("/crear") //CREATE-POST
    public AdminDTO agregarAdmin( @NonNull @RequestBody AdminDTO admin) {
        return servicio.save(admin);
    }
	
	
    @ResponseBody @GetMapping("/listar")
    public List<AdminDTO> getAllAdmin() {
    	List<AdminDTO> l = servicio.findAll();
    	return l;
    }
    
    
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String contra = loginDTO.getContra();

        Optional<AdminDTO> result = servicio.findByEmailAndContra(email, contra);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
    
}