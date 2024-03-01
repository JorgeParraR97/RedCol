package com.practica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.dto.TarifaDTO;

@Service
public interface ITarifaService {
	
	
	public List<TarifaDTO> findAll();
    public Optional<TarifaDTO> findById(int id);
    public TarifaDTO save(TarifaDTO tarifa);
    public void deleteById(int id);

}
