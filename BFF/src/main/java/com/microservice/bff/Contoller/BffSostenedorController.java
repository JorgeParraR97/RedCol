package com.microservice.bff.Contoller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bff.DTO.AdminDTO;
import com.microservice.bff.DTO.ColegioDTO;

import com.microservice.bff.DTO.SostenedorDTO;
import com.microservice.bff.Service.ISostenedorService;

@RestController
@RequestMapping("/api/bff/sostenedor")
public class BffSostenedorController {
	
    private final Logger logger = LoggerFactory.getLogger(BffSostenedorController.class);


	@Autowired
	ISostenedorService sostenedorService;
	

	@GetMapping("/findAll")
    public List<SostenedorDTO> SostenedorFindAll(){
		return sostenedorService.SostenedorFindAll(); 
    }
	
	@PostMapping("/create")
	public ResponseEntity<String> SostenedorSave(@RequestBody @Valid SostenedorDTO sostenedor) {
	    try {
	        if (sostenedor == null) {
	            logger.error("Datos de admin no válidos. Rechazando la solicitud. Datos: {}", sostenedor);
	            return ResponseEntity.badRequest().body("Datos de admin no válidos");
	        }

	        logger.info("Recibida solicitud para crear admin. Datos: {}", sostenedor);

	        sostenedorService.SostenedorSave(sostenedor);

	        logger.info("Admin creado exitosamente. Datos: {}", sostenedor);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Admin creado exitosamente");
	    } catch (Exception e) {
	        logger.error("Error interno al procesar la solicitud de creación de admin. Datos: {}", sostenedor, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	@GetMapping("/colegio/{sostenedorid}")
    public ResponseEntity<List<ColegioDTO>> getColegios(@PathVariable("sostenedorid") int sostenedorid) {
		
		ResponseEntity<List<ColegioDTO>> responseEntity = sostenedorService.getColegios(sostenedorid);
        return responseEntity;
    }
	
	@GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<SostenedorDTO>> findById(@PathVariable("id") int id){
		
		return sostenedorService.findById(id);
	}
	
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarSostenedor(@PathVariable int id, @RequestBody SostenedorDTO sostenedorActualizado) {
		
		ResponseEntity<String> responseEntity = sostenedorService.actualizarSostenedor(id, sostenedorActualizado);
        return responseEntity;
    }
	
	
}