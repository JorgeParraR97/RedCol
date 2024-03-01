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

import com.microservice.bff.DTO.TarifaDTO;



@FeignClient(name = "TarifaMicro")
public interface TarifaClient {
	
	@GetMapping("/api/tarifa/listar")
    public List<TarifaDTO> findAll();
	
	@PostMapping("/api/tarifa/crear")
	public TarifaDTO save(@RequestBody @Valid TarifaDTO tarifa);
	
  
    @GetMapping("api/tarifa/buscar/{id}")
    public ResponseEntity<Optional<TarifaDTO>> findById(@PathVariable("id") int id);
    
    @PutMapping("api/tarifa/actualizar/{id}")
    ResponseEntity<String> actualizarTarifa(@PathVariable int id, @RequestBody TarifaDTO tarifaActualizado);
    
	@DeleteMapping("/api/tarifa/borrar/{id}")
	public void delete(@PathVariable int id);
}

	
