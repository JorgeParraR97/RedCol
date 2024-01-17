package com.practica.AdminRC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.AdminRC.dto.AdminDTO;
import com.practica.AdminRC.entity.AdminEntity;



@Service
public interface IAdminService {
	
    public List<AdminDTO> findAll();
    public Optional<AdminDTO> findById(int id);
    public AdminDTO save(AdminDTO admin);
    public void delete(int id);
	public Optional<AdminDTO> findByEmailAndContra(String email, String contra);
	public Optional<AdminEntity> findByEmail(String email);

}
