package com.practica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practica.DTO.EstablecimientoDTO;
import com.practica.service.IEstablecimientoService;





@RestController
@RequestMapping("api/establecimiento")
public class EstablecimientoController {
	
	@Autowired
	private IEstablecimientoService servicio;
	
	
	
    @ResponseBody @PostMapping("/crear") //CREATE-POST
    public EstablecimientoDTO agregarEstablecimiento( @NonNull @RequestBody EstablecimientoDTO establecimiento) {
        return servicio.save(establecimiento);
    }
	
	
    @ResponseBody @GetMapping("/listar")
    public List<EstablecimientoDTO> getAllEstablecimiento() {
    	List<EstablecimientoDTO> l = servicio.findAll();
    	return l;
    }
    
    
    @GetMapping("/ls/{sostenedorId}")
    public ResponseEntity<List<EstablecimientoDTO>> findBySostenedorId(@PathVariable int sostenedorId) {
        try {
            List<EstablecimientoDTO> establecimientos = servicio.findBySostenedorId(sostenedorId);

            if (establecimientos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(establecimientos, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarEstablecimiento(@PathVariable int id, @RequestBody EstablecimientoDTO establecimientoActualizado) {
		Optional<EstablecimientoDTO> optionalEstablecimientoExistente = servicio.findById(id);
        if (optionalEstablecimientoExistente.isEmpty()) {
            return new ResponseEntity<>("Establecimiento no encontrado", HttpStatus.NOT_FOUND);
        }
        
        EstablecimientoDTO establecimientoExistente = optionalEstablecimientoExistente.get();
        establecimientoExistente.setNombre(establecimientoActualizado.getNombre());
        establecimientoExistente.setRbd(establecimientoActualizado.getRbd());
        establecimientoExistente.setDireccion(establecimientoActualizado.getDireccion());
        establecimientoExistente.setContactoemail(establecimientoActualizado.getContactoemail());
        establecimientoExistente.setContactonombre(establecimientoActualizado.getContactonombre());
        establecimientoExistente.setContactocargo(establecimientoActualizado.getContactocargo());
        establecimientoExistente.setContactotelefono(establecimientoActualizado.getContactotelefono());
        servicio.save(establecimientoExistente);
        return new ResponseEntity<>("Establecimiento actualizado con éxito. ID: " + establecimientoExistente.getId(), HttpStatus.OK);
    }
    
    
	@DeleteMapping("/borrar/{id}")
	 public ResponseEntity<?> deleteById(@PathVariable int id) {
	    try {
	        servicio.deleteById(id);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	    	return ResponseEntity.status(500).body("Error al eliminar el establecimiento");
	        	}
	    }
	
	

}