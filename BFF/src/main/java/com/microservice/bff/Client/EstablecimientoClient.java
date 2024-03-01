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


import com.microservice.bff.DTO.EstablecimientoDTO;


@FeignClient(name = "EstablecimientoMicro")
public interface EstablecimientoClient {
	
	@GetMapping("/api/establecimiento/listar")
    public List<EstablecimientoDTO> findAll();
	
	@PostMapping("/api/establecimiento/crear")
	public EstablecimientoDTO save(@RequestBody @Valid EstablecimientoDTO establecimiento);
	
   
    @GetMapping("api/establecimiento/buscar/{id}")
    public ResponseEntity<Optional<EstablecimientoDTO>> findById(@PathVariable("id") int id);
    
    @PutMapping("api/establecimiento/actualizar/{id}")
    ResponseEntity<String> actualizarEstablecimiento(@PathVariable int id, @RequestBody EstablecimientoDTO establecimientoActualizado);
    
    @GetMapping("/api/establecimiento/ls/{sostenedorId}")
    public ResponseEntity<List<EstablecimientoDTO>> findBySostenedorId(@PathVariable("sostenedorId") int sostenedorId);
    
	@DeleteMapping("/api/establecimiento/borrar/{id}")
	public void delete(@PathVariable int id);

 }