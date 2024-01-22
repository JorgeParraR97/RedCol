package com.practica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practica.dto.ColegioDTO;

@Service
public interface IColegioService {
	
	
	public List<ColegioDTO> findAllREST();
	
	public ResponseEntity<String> saveREST(ColegioDTO c);
	
	public void actualizarColegio(ColegioDTO colegioDTO);
	
	public ColegioDTO deleteREST(int id);

}
