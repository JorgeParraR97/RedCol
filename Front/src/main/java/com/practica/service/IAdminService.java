package com.practica.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practica.dto.AdminDTO;
import com.practica.dto.LoginDTO;

@Service
public interface IAdminService {
	
	public LoginDTO loginREST(LoginDTO loginDTO);
	
	public ResponseEntity<String> saveREST(AdminDTO a);
	
	

}
