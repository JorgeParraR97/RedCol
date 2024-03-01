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

import com.microservice.bff.DTO.TarifaDTO;
import com.microservice.bff.Service.ITarifaService;



@RestController
@RequestMapping("/api/bff/tarifa")
public class BffTarifaController {
	
    private final Logger logger = LoggerFactory.getLogger(BffTarifaController.class);


	@Autowired
	ITarifaService tarifaService;
	

	@GetMapping("/findAll")
    public List<TarifaDTO> TarifaFindAll(){
		return tarifaService.TarifaFindAll(); 
    }
	
	@PostMapping("/create")
	public ResponseEntity<String> TarifaSave(@RequestBody @Valid TarifaDTO tarifa) {
	    try {
	        if (tarifa == null) {
	            logger.error("Datos de tarifa no válidos. Rechazando la solicitud. Datos: {}", tarifa);
	            return ResponseEntity.badRequest().body("Datos de tarifa no válidos");
	        }

	        logger.info("Recibida solicitud para crear tarifa. Datos: {}", tarifa);

	        tarifaService.TarifaSave(tarifa);

	        logger.info("Tarifa creada exitosamente. Datos: {}", tarifa);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Tarifa creado exitosamente");
	    } catch (Exception e) {
	        logger.error("Error interno al procesar la solicitud de creación de tarifa. Datos: {}", tarifa, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	
	@GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<TarifaDTO>> findById(@PathVariable("id") int id){
		
		return tarifaService.findById(id);
	}
	
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarTarifa(@PathVariable int id, @RequestBody TarifaDTO tarifaActualizado) {
		
		ResponseEntity<String> responseEntity = tarifaService.actualizarTarifa(id, tarifaActualizado);
        return responseEntity;
    }
	
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> TarifaDelete(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        tarifaService.TarifaDelete(id);
        return ResponseEntity.noContent().build();
    }
	
	
}