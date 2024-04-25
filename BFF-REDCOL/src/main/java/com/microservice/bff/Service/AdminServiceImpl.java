package com.microservice.bff.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.Client.AdminClient;
import com.microservice.bff.DTO.AdminDTO;
import com.microservice.bff.DTO.LoginDTO;


@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	AdminClient adminClient;

	@Override
	public List<AdminDTO> AdminFindAll() {
		return adminClient.findAll();

	}

	@Override
	public void AdminSave(AdminDTO admin) {
		adminClient.save(admin);
		
	}

	@Override
	public ResponseEntity<LoginDTO> AdminLogin(LoginDTO loginDTO) {
		return adminClient.login(loginDTO);
	}
	
}