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

import com.microservice.bff.DTO.PagosDTO;
import com.microservice.bff.Service.IPagosService;


@RestController
@RequestMapping("/api/bff/pagos")
public class BffPagosController {
	
	 private final Logger logger = LoggerFactory.getLogger(BffPagosController.class);


		@Autowired
		IPagosService pagosService;
		
	
	@GetMapping("/findAll")
    public List<PagosDTO> PagosFindAll(){
		return pagosService.PagosFindAll(); 
    }
	
	@PostMapping("/create")
	public ResponseEntity<String> PagosSave(@RequestBody @Valid PagosDTO pagos) {
	    try {
	        if (pagos == null) {
	            logger.error("Datos de pagos no válidos. Rechazando la solicitud. Datos: {}", pagos);
	            return ResponseEntity.badRequest().body("Datos de pagos no válidos");
	        }

	        logger.info("Recibida solicitud para crear pagos. Datos: {}", pagos);

	        pagosService.PagosSave(pagos);

	        logger.info("Pagos creado exitosamente. Datos: {}", pagos);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Pagos creado exitosamente");
	    } catch (Exception e) {
	        logger.error("Error interno al procesar la solicitud de creación de pagos. Datos: {}", pagos, e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	
	
	@GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<PagosDTO>> findById(@PathVariable("id") int id){
		
		return pagosService.findById(id);
	}
	
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarPagos(@PathVariable int id, @RequestBody PagosDTO pagosActualizado) {
		
		ResponseEntity<String> responseEntity = pagosService.actualizarPagos(id, pagosActualizado);
        return responseEntity;
    }
	
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> PagosDelete(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        pagosService.PagosDelete(id);
        return ResponseEntity.noContent().build();
    }

}
