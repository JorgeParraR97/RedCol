package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.microservice.bff.DTO.EstablecimientoDTO;
import com.microservice.bff.DTO.LoginDTO;
import com.microservice.bff.DTO.SostenedorDTO;

@Service
public interface ISostenedorService {
	
	List<SostenedorDTO> SostenedorFindAll();
	void SostenedorSave(SostenedorDTO sostenedor);
	void SostenedorDelete(int id);
	ResponseEntity<List<EstablecimientoDTO>> getEstablecimientos(@PathVariable("sostenedorid") int sostenedorid);
	ResponseEntity<Optional<SostenedorDTO>> findById(int id);
	ResponseEntity<String> actualizarSostenedor(@PathVariable int id, @RequestBody SostenedorDTO sostenedorActualizado);
	ResponseEntity<LoginDTO> SostenedorLogin(LoginDTO loginDTO);

}