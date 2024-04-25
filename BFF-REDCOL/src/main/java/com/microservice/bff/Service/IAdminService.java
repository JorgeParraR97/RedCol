package com.microservice.bff.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.DTO.AdminDTO;
import com.microservice.bff.DTO.LoginDTO;


@Service
public interface IAdminService {
	
	List<AdminDTO> AdminFindAll();
	void AdminSave(AdminDTO admin);
	ResponseEntity<LoginDTO> AdminLogin(LoginDTO loginDTO);
	
}