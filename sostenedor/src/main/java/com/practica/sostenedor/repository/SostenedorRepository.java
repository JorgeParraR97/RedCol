package com.practica.sostenedor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.practica.sostenedor.dto.SostenedorDTO;
import com.practica.sostenedor.entity.SostenedorEntity;

@Repository
public interface SostenedorRepository extends JpaRepository<SostenedorEntity, Integer>{
	
	Optional<SostenedorDTO> findByEmailAndContra(String email, String contra);
	Optional<SostenedorEntity> findByEmail(String email);

}
