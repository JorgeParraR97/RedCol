package com.microservice.bff.Contoller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bff.DTO.EstablecimientoDTO;
import com.microservice.bff.Service.IEstablecimientoService;


@RestController
@RequestMapping("/api/bff/establecimiento")
public class BffEstablecimientoController {
	
    private final Logger logger = LoggerFactory.getLogger(BffEstablecimientoController.class);


	@Autowired
	IEstablecimientoService establecimientoService;
	

	@GetMapping("/findAll")
    public List<EstablecimientoDTO> EstablecimientoFindAll(){
		return establecimientoService.EstablecimientoFindAll(); 
    }
	
	@PostMapping("/create")
    public ResponseEntity<Void> EstablecimientoSave(@RequestBody @Valid EstablecimientoDTO establecimiento) {
        if (establecimiento == null) {
            logger.error("Solicitud de creación de establecimiento con datos nulos. Rechazando la solicitud.");
            return ResponseEntity.badRequest().build();
        }

        try {
            logger.info("Recibida solicitud para crear establecimiento. Datos: {}", establecimiento);

            establecimientoService.EstablecimientoSave(establecimiento);

            logger.info("Establecimiento creado exitosamente.");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación de Establecimiento.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	
	@GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<EstablecimientoDTO>> findById(@PathVariable("id") int id){
		
		return establecimientoService.findById(id);
	}
	
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarEstablecimiento(@PathVariable int id, @RequestBody EstablecimientoDTO establecimientoActualizado) {
		
		ResponseEntity<String> responseEntity = establecimientoService.actualizarEstablecimiento(id, establecimientoActualizado);
        return responseEntity;
    }
	
	@GetMapping("/ls/{sostenedorId}")
	public ResponseEntity<List<EstablecimientoDTO>> findBySostenedorId(@PathVariable int sostenedorId) {
	    try {
	        ResponseEntity<List<EstablecimientoDTO>> response = establecimientoService.findBySostenedorId(sostenedorId);

	        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        List<EstablecimientoDTO> establecimientos = response.getBody();
	        return new ResponseEntity<>(establecimientos, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> ColegioDelete(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        establecimientoService.EstablecimientoDelete(id);
        return ResponseEntity.noContent().build();
    }
	
	
}