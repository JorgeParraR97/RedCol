package com.microservice.bff.Client;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.DTO.ColegioDTO;


@FeignClient(name = "ColegioMicro")
public interface ColegioClient {
	
	@GetMapping("/api/colegio/listar")
    public List<ColegioDTO> findAll();
	
	@PostMapping("/api/colegio/crear")
	public ColegioDTO save(@RequestBody @Valid ColegioDTO colegio);
	
   
    @GetMapping("api/colegio/buscar/{id}")
    public ResponseEntity<Optional<ColegioDTO>> findById(@PathVariable("id") int id);
    
    @PutMapping("api/colegio/actualizar/{id}")
    ResponseEntity<String> actualizarColegio(@PathVariable int id, @RequestBody ColegioDTO colegioActualizado);
    
    @GetMapping("/api/colegio/ls/{sostenedorId}")
    public ResponseEntity<List<ColegioDTO>> findBySostenedorId(@PathVariable("sostenedorId") int sostenedorId);

 }