package com.practica.sostenedor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practica.sostenedor.dto.ColegioDTO;
import com.practica.sostenedor.dto.LoginDTO;
import com.practica.sostenedor.dto.SostenedorDTO;

import com.practica.sostenedor.service.ISostenedorService;

@RestController
@RequestMapping("api/sostenedor")
public class SostenedorController {
	
	private final Logger logger = LoggerFactory.getLogger(SostenedorController.class);
	
	@Autowired
	private ISostenedorService servicio;
	
	
	
	@PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public SostenedorDTO agregarSostenedor(@RequestBody SostenedorDTO sostenedor) {
        if (sostenedor == null) {
            logger.error("Solicitud de creación de sostenedor con datos nulos. No se puede procesar.");
            throw new IllegalArgumentException("Los datos del sostenedor no pueden ser nulos.");
        }

        try {
            logger.info("Recibida solicitud para crear sostenedor. Datos: {}", sostenedor);

            SostenedorDTO sostenedorGuardado = servicio.save(sostenedor);

            logger.info("Sostenedor creado exitosamente. ID: {}", sostenedorGuardado.getId());
            return sostenedorGuardado;
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación de sostenedor.", e);
            throw new RuntimeException("Error al procesar la solicitud de creación de sostenedor.", e);
        }
    }
	
	
    @ResponseBody @GetMapping("/listar")
    public List<SostenedorDTO> getAllSostenedor() {
    	List<SostenedorDTO> l = servicio.findAll();
    	return l;
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<SostenedorDTO>> findById(@PathVariable("id") int id) {
        Optional<SostenedorDTO> s = servicio.findById(id);
        return ResponseEntity.ok(s);
    }
    
    @ResponseBody @GetMapping("colegio/{sostenedorid}")
    public ResponseEntity<List<ColegioDTO>> getColegios(@PathVariable("sostenedorid") int sostenedorid){
    	Optional<SostenedorDTO> sostenedor = servicio.findById(sostenedorid);
    	if(sostenedor == null)
    		return ResponseEntity.notFound().build();
    	List<ColegioDTO> colegios = servicio.getColegios(sostenedorid);
    	return ResponseEntity.ok(colegios);
    }
    
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarSostenedor(@PathVariable int id, @RequestBody SostenedorDTO sostenedorActualizado) {
		Optional<SostenedorDTO> optionalSostenedorExistente = servicio.findById(id);
        if (optionalSostenedorExistente.isEmpty()) {
            return new ResponseEntity<>("Sostenedor no encontrado", HttpStatus.NOT_FOUND);
        }
        
        SostenedorDTO sostenedorExistente = optionalSostenedorExistente.get();
        sostenedorExistente.setNombre(sostenedorActualizado.getNombre());
        sostenedorExistente.setRepresentante(sostenedorActualizado.getRepresentante());
        sostenedorExistente.setRut(sostenedorActualizado.getRut());
        sostenedorExistente.setEmail(sostenedorActualizado.getEmail());
        sostenedorExistente.setContrasena(sostenedorActualizado.getContrasena());
        servicio.save(sostenedorExistente);
        return new ResponseEntity<>("Sostenedor actualizado con éxito. ID: " + sostenedorExistente.getId(), HttpStatus.OK);
    }
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String contra = loginDTO.getContrasena();

        Optional<SostenedorDTO> result = servicio.findByEmailAndContrasena(email, contra);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
	
	@DeleteMapping("/borrar/{id}")
	 public ResponseEntity<?> deleteById(@PathVariable int id) {
	    try {
	        servicio.deleteById(id);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	    	return ResponseEntity.status(500).body("Error al eliminar el proyecto");
	        	}
	    }
	
	

	

}
