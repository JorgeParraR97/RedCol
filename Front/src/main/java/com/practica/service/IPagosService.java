package com.practica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practica.dto.PagosDTO;
import com.practica.dto.TipopagoDTO;


@Service
public interface IPagosService {
	
    public List<PagosDTO> findAllREST();
	
	public ResponseEntity<String> saveREST(PagosDTO p);
	
	public void actualizarPagos(PagosDTO pagosDTO);
	
	public PagosDTO deleteREST(int id);
	
	public List<TipopagoDTO> findtpAllREST();

}
