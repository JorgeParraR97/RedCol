package com.practica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.practica.dto.EstablecimientoDTO;
import com.practica.dto.LoginDTO;
import com.practica.dto.SostenedorDTO;


@Service
public interface ISostenedorService {
	
	public List<SostenedorDTO> findAllREST();
	
	public ResponseEntity<String> saveREST(SostenedorDTO s);
	
	public void actualizarSostenedor(SostenedorDTO sostenedorDTO);
	
	public SostenedorDTO deleteREST(int id);
	
	public List<EstablecimientoDTO> getEstablecimientos(int sostenedorId);
	
	public LoginDTO loginsosREST(LoginDTO loginDTO);

}
