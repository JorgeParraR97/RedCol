package com.microservice.bff.Client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.DTO.AdminDTO;
import com.microservice.bff.DTO.LoginDTO;

@FeignClient(name = "AdminMicro")
public interface AdminClient {
	
	@GetMapping("/api/admin/listar")
    public List<AdminDTO> findAll();
	
	@PostMapping("/api/admin/crear")
	public AdminDTO save(@RequestBody @Valid AdminDTO admin);
	
	@PostMapping("/api/admin/login")
	public ResponseEntity<LoginDTO> login(@RequestBody @Valid LoginDTO loginDTO);

	
}
	