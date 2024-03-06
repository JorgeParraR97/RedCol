package com.practica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practica.dto.TarifaDTO;

@Service
public interface ITarifaService {
	
	public List<TarifaDTO> findAllREST();
	
	public ResponseEntity<String> saveREST(TarifaDTO t);
	
	public void actualizarTarifa(TarifaDTO tarifaDTO);
	
	public TarifaDTO deleteREST(int id);
	
}