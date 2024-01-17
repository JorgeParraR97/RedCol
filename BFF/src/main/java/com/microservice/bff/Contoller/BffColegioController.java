package com.microservice.bff.Contoller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.microservice.bff.DTO.ColegioDTO;
import com.microservice.bff.Service.IColegioService;


@RestController
@RequestMapping("/api/bff/colegio")
public class BffColegioController {
	
    private final Logger logger = LoggerFactory.getLogger(BffColegioController.class);


	@Autowired
	IColegioService colegioService;
	

	@GetMapping("/findAll")
    public List<ColegioDTO> ColegioFindAll(){
		return colegioService.ColegioFindAll(); 
    }
	
	@PostMapping("/create")
    public ResponseEntity<Void> ColegioSave(@RequestBody @Valid ColegioDTO colegio) {
        if (colegio == null) {
            logger.error("Solicitud de creación de colegio con datos nulos. Rechazando la solicitud.");
            return ResponseEntity.badRequest().build();
        }

        try {
            logger.info("Recibida solicitud para crear sostenedor. Datos: {}", colegio);

            colegioService.ColegioSave(colegio);

            logger.info("Colegio creado exitosamente.");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación de colegio.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	
	@GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<ColegioDTO>> findById(@PathVariable("id") int id){
		
		return colegioService.findById(id);
	}
	
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarColegio(@PathVariable int id, @RequestBody ColegioDTO colegioActualizado) {
		
		ResponseEntity<String> responseEntity = colegioService.actualizarColegio(id, colegioActualizado);
        return responseEntity;
    }
	
	@GetMapping("/ls/{sostenedorId}")
	public ResponseEntity<List<ColegioDTO>> findBySostenedorId(@PathVariable int sostenedorId) {
	    try {
	        ResponseEntity<List<ColegioDTO>> response = colegioService.findBySostenedorId(sostenedorId);

	        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        List<ColegioDTO> colegios = response.getBody();
	        return new ResponseEntity<>(colegios, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
}