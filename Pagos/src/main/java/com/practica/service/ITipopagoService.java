package com.practica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practica.dto.TipopagoDTO;

@Service
public interface ITipopagoService {
	
	public List<TipopagoDTO> findAll();
	
}