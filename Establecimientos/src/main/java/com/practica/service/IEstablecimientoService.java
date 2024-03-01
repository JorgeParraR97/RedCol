package com.practica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.DTO.EstablecimientoDTO;


@Service
public interface IEstablecimientoService {
	
    public List<EstablecimientoDTO> findAll();
    public Optional<EstablecimientoDTO> findById(int id);
    public EstablecimientoDTO save(EstablecimientoDTO establecimiento);
    public void deleteById(int id);
    
    List<EstablecimientoDTO> findBySostenedorId(int sostenedorId);
	

}