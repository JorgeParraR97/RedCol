package com.microservice.bff.Client;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.microservice.bff.DTO.ColegioDTO;

import com.microservice.bff.DTO.SostenedorDTO;

@FeignClient(name = "SostenedorMicro")
public interface SostenedorClient {
	
	@GetMapping("/api/sostenedor/listar")
    public List<SostenedorDTO> findAll();
	
	@PostMapping("/api/sostenedor/crear")
	public SostenedorDTO save(@RequestBody @Valid SostenedorDTO sostenedor);
	
    @GetMapping("api/sostenedor/colegio/{sostenedorid}")
    public ResponseEntity<List<ColegioDTO>> getColegios(@PathVariable("sostenedorid") int sostenedorid);
    
    @GetMapping("api/sostenedor/buscar/{id}")
    public ResponseEntity<Optional<SostenedorDTO>> findById(@PathVariable("id") int id);
    
    @PutMapping("api/sostenedor/actualizar/{id}")
    ResponseEntity<String> actualizarSostenedor(@PathVariable int id, @RequestBody SostenedorDTO sostenedorActualizado);
    
	@DeleteMapping("/api/sostenedor/borrar/{id}")
	public void delete(@PathVariable int id);
}

	

