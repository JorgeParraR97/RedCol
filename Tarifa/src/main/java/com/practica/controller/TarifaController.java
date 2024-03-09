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

import com.practica.dto.MapamensualidadDTO;
import com.practica.dto.TarifaDTO;
import com.practica.service.IMapamensualidadService;
import com.practica.service.ITarifaService;

@RestController
@RequestMapping("api/tarifa")
public class TarifaController {
	
	
	private final Logger logger = LoggerFactory.getLogger(TarifaController.class);
	
	
	@Autowired
	private ITarifaService servicio;
	
	@Autowired
	private IMapamensualidadService mmservicio;
	
	
	@PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public TarifaDTO agregarTarifa(@RequestBody TarifaDTO tarifa) {
        if (tarifa == null) {
            logger.error("Solicitud de creación de tarifa con datos nulos. No se puede procesar.");
            throw new IllegalArgumentException("Los datos del tarifa no pueden ser nulos.");
        }

        try {
            logger.info("Recibida solicitud para crear tarifa. Datos: {}", tarifa);

            TarifaDTO tarifaGuardado = servicio.save(tarifa);

            logger.info("Tarifa creado exitosamente. ID: {}", tarifaGuardado.getId());
            return tarifaGuardado;
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación de tarifa.", e);
            throw new RuntimeException("Error al procesar la solicitud de creación de tarifa.", e);
        }
    }
	
	
    @ResponseBody @GetMapping("/listar")
    public List<TarifaDTO> getAllTarifa() {
    	List<TarifaDTO> l = servicio.findAll();
    	return l;
    }
    
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<TarifaDTO>> findById(@PathVariable("id") int id) {
        Optional<TarifaDTO> t = servicio.findById(id);
        return ResponseEntity.ok(t);
    }
    
    
	@PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarTarifa(@PathVariable int id, @RequestBody TarifaDTO tarifaActualizado) {
		Optional<TarifaDTO> optionalTarifaExistente = servicio.findById(id);
        if (optionalTarifaExistente.isEmpty()) {
            return new ResponseEntity<>("Tarifa no encontrado", HttpStatus.NOT_FOUND);
        }
        
        TarifaDTO tarifaExistente = optionalTarifaExistente.get();
        tarifaExistente.setPeriodo(tarifaActualizado.getPeriodo());
        tarifaExistente.setDescripcion(tarifaActualizado.getDescripcion());
        tarifaExistente.setMonto(tarifaActualizado.getMonto());
        servicio.save(tarifaExistente);
        return new ResponseEntity<>("Tarifa actualizado con éxito. ID: " + tarifaExistente.getId(), HttpStatus.OK);
    }
	
	
	@DeleteMapping("/borrar/{id}")
	 public ResponseEntity<?> deleteById(@PathVariable int id) {
	    try {
	        servicio.deleteById(id);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	    	return ResponseEntity.status(500).body("Error al eliminar la Tarifa");
	        	}
	    }
	
	
	@PostMapping("/crearmm")
    @ResponseStatus(HttpStatus.CREATED)
    public MapamensualidadDTO agregarMapamensualidad(@RequestBody MapamensualidadDTO mapa) {
        if (mapa == null) {
            logger.error("Solicitud de creación de mapa con datos nulos. No se puede procesar.");
            throw new IllegalArgumentException("Los datos del mapa no pueden ser nulos.");
        }

        try {
            logger.info("Recibida solicitud para crear mapa. Datos: {}", mapa);

            MapamensualidadDTO mapaGuardado = mmservicio.save(mapa);

            logger.info("Mapa creado exitosamente. ID: {}", mapaGuardado.getId());
            return mapaGuardado;
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de creación de mapa.", e);
            throw new RuntimeException("Error al procesar la solicitud de creación de mapa.", e);
        }
    }
	
	
    @ResponseBody @GetMapping("/listarmm")
    public List<MapamensualidadDTO> getAllMapa() {
    	List<MapamensualidadDTO> l = mmservicio.findAll();
    	return l;
    }
    
    
    @GetMapping("/buscarmm/{id}")
    public ResponseEntity<Optional<MapamensualidadDTO>> findmmById(@PathVariable("id") int id) {
        Optional<MapamensualidadDTO> t = mmservicio.findById(id);
        return ResponseEntity.ok(t);
    }
    
    
	@PutMapping("/actualizarmm/{id}")
    public ResponseEntity<String> actualizarMapa(@PathVariable int id, @RequestBody MapamensualidadDTO mapaActualizado) {
		Optional<MapamensualidadDTO> optionalMapaExistente = mmservicio.findById(id);
        if (optionalMapaExistente.isEmpty()) {
            return new ResponseEntity<>("Mapa no encontrado", HttpStatus.NOT_FOUND);
        }
        
        MapamensualidadDTO mapaExistente = optionalMapaExistente.get();
        mapaExistente.setMes(mapaActualizado.getMes());
        mapaExistente.setPeriodo(mapaActualizado.getPeriodo());
        mapaExistente.setEstablecimientoId(mapaActualizado.getEstablecimientoId());
        mapaExistente.setSostenedorId(mapaActualizado.getSostenedorId());
        mapaExistente.setTarifaId(mapaActualizado.getTarifaId());
        mmservicio.save(mapaExistente);
        return new ResponseEntity<>("Mapa actualizado con éxito. ID: " + mapaExistente.getId(), HttpStatus.OK);
    }
	
	
	@DeleteMapping("/borrarmm/{id}")
	 public ResponseEntity<?> deletemmById(@PathVariable int id) {
	    try {
	        mmservicio.deleteById(id);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	    	return ResponseEntity.status(500).body("Error al eliminar el Mapa");
	        	}
	    }
	
	
}

