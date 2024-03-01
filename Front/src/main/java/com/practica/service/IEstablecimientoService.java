package com.practica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practica.dto.EstablecimientoDTO;



@Service
public interface IEstablecimientoService {
	
	
	public List<EstablecimientoDTO> findAllREST();
	
	public ResponseEntity<String> saveREST(EstablecimientoDTO e);
	
	public void actualizarEstablecimiento(EstablecimientoDTO establecimientoDTO);
	
	public EstablecimientoDTO deleteREST(int id);

}
