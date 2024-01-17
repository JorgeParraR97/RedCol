package com.practica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.practica.dto.SostenedorDTO;


@Service
public interface ISostenedorService {
	
	public List<SostenedorDTO> findAllREST();
	
	public ResponseEntity<String> saveREST(SostenedorDTO s);

}
