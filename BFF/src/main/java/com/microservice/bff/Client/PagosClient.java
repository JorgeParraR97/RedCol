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

import com.microservice.bff.DTO.PagosDTO;
import com.microservice.bff.DTO.TipopagoDTO;



@FeignClient(name = "PagosMicro")
public interface PagosClient {
	
	@GetMapping("/api/pagos/listar")
    public List<PagosDTO> findAll();
	
	@PostMapping("/api/pagos/crear")
	public PagosDTO save(@RequestBody @Valid PagosDTO pagos);

    
    @GetMapping("api/pagos/buscar/{id}")
    public ResponseEntity<Optional<PagosDTO>> findById(@PathVariable("id") int id);
    
    @PutMapping("api/pagos/actualizar/{id}")
    ResponseEntity<String> actualizarPagos(@PathVariable int id, @RequestBody PagosDTO pagosActualizado);
    
	@DeleteMapping("/api/pagos/borrar/{id}")
	public void delete(@PathVariable int id);
	
	@GetMapping("/api/pagos/listartp")
    public List<TipopagoDTO> findAlltp();
	
	
}
