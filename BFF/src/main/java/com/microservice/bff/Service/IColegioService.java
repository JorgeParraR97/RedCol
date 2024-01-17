package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.DTO.ColegioDTO;


@Service
public interface IColegioService {
	
	List<ColegioDTO> ColegioFindAll();
	void ColegioSave(ColegioDTO colegio);
	ResponseEntity<Optional<ColegioDTO>> findById(int id);
	ResponseEntity<String> actualizarColegio(@PathVariable int id, @RequestBody ColegioDTO colegioActualizado);
	ResponseEntity<List<ColegioDTO>> findBySostenedorId(@PathVariable int sostenedorId);

}