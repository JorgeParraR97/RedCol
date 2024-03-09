package com.practica.AdminRC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.AdminRC.dto.AdminDTO;
import com.practica.AdminRC.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
	
	Optional<AdminDTO> findByEmailAndContrasena(String email, String contrasena);
	Optional<AdminEntity> findByEmail(String email);

}
