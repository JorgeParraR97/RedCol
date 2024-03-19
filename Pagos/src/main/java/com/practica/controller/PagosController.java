package com.practica.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practica.dto.PagosDTO;
import com.practica.dto.TipopagoDTO;
import com.practica.service.IPagosService;
import com.practica.service.ITipopagoService;


@RestController
@RequestMapping("api/pagos")
public class PagosController {
	
	
	private final Logger logger = LoggerFactory.getLogger(PagosController.class);
	
	
	@Autowired
	private IPagosService servicio;
	
	
	@Autowired
	private ITipopagoService tpservicio; 
	
	
	@PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public PagosDTO agregarPagos(@RequestBody PagosDTO pagos) {
        if (pagos == null) {
            logger.error("Solicitud de creación de pagos con datos nulos. No se puede procesar.");
            throw new IllegalArgumentException("Los datos del pagos no pueden ser nulos.");
        }

        try {
            logger.info("Recibida solicitud para crear pagos. Datos: {}", pagos);

            PagosDTO pagosGuardado = servicio.save(pagos);

            logger.info("Pagos creado exitosamente. ID: {}", pagosGuardado.getId());
            return pagosGuardado;
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación de pagos.", e);
            throw new RuntimeException("Error al procesar la solicitud de creación de pagos.", e);
        }
    }
	
	
    @ResponseBody @GetMapping("/listar")
    public List<PagosDTO> getAllPagos() {
    	List<PagosDTO> l = servicio.findAll();
    	return l;
    }
    
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<PagosDTO>> findById(@PathVariable("id") int id) {
        Optional<PagosDTO> p = servicio.findById(id);
        return ResponseEntity.ok(p);
    }
    
    
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarPagos(@PathVariable int id, @RequestBody PagosDTO pagosActualizado) {
		Optional<PagosDTO> optionalPagosExistente = servicio.findById(id);
        if (optionalPagosExistente.isEmpty()) {
            return new ResponseEntity<>("Pagos no encontrado", HttpStatus.NOT_FOUND);
        }
        
        PagosDTO pagosExistente = optionalPagosExistente.get();
        pagosExistente.setNumerodocumento(pagosActualizado.getNumerodocumento());
        pagosExistente.setTipopago(pagosActualizado.getTipopago());
        pagosExistente.setMonto(pagosActualizado.getMonto());
        pagosExistente.setFecharegistro(pagosActualizado.getFecharegistro());
        pagosExistente.setEntidadId(pagosActualizado.getEntidadId());
        pagosExistente.setPeriodo(pagosActualizado.getPeriodo());
        pagosExistente.setMes(pagosActualizado.getMes());
        pagosExistente.setPagado(pagosActualizado.getPagado());
        pagosExistente.setSaldo(pagosActualizado.getSaldo());
        servicio.save(pagosExistente);
        return new ResponseEntity<>("Pagos actualizado con éxito. ID: " + pagosExistente.getId(), HttpStatus.OK);
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
	
	
    @ResponseBody @GetMapping("/listartp")
    public List<TipopagoDTO> getAllTipopago() {
    	List<TipopagoDTO> l = tpservicio.findAll();
    	return l;
    }
	
	
	
	
}

 


