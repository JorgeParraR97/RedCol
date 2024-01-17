package com.practica.sostenedor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.sostenedor.dto.ColegioDTO;

import com.practica.sostenedor.dto.SostenedorDTO;
import com.practica.sostenedor.entity.SostenedorEntity;



@Service
public interface ISostenedorService {
	
    public List<SostenedorDTO> findAll();
    public Optional<SostenedorDTO> findById(int id);
    public SostenedorDTO save(SostenedorDTO sostenedor);
    public void delete(int id);
    public List<ColegioDTO> getColegios(int sostenedorid);
    public Optional<SostenedorDTO> findByEmailAndContra(String email, String contra);
    public Optional<SostenedorEntity> findByEmail(String email);
    
	
	

}
