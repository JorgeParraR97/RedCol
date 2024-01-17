package com.practica.colegios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.colegios.dto.ColegioDTO;



@Service
public interface IColegioService {
	
    public List<ColegioDTO> findAll();
    public Optional<ColegioDTO> findById(int id);
    public ColegioDTO save(ColegioDTO colegio);
    public void delete(int id);
    
    List<ColegioDTO> findBySostenedorId(int sostenedorId);
	

}
