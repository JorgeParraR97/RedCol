package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.DTO.EstablecimientoDTO;


@Service
public interface IEstablecimientoService {
	
	List<EstablecimientoDTO> EstablecimientoFindAll();
	void EstablecimientoSave(EstablecimientoDTO establecimiento);
	void EstablecimientoDelete(int id);
	ResponseEntity<Optional<EstablecimientoDTO>> findById(int id);
	ResponseEntity<String> actualizarEstablecimiento(@PathVariable int id, @RequestBody EstablecimientoDTO establecimientoActualizado);
	ResponseEntity<List<EstablecimientoDTO>> findBySostenedorId(@PathVariable int sostenedorId);

}