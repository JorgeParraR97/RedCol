package com.practica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.dto.PagosDTO;

@Service
public interface IPagosService {
	
	public List<PagosDTO> findAll();
    public Optional<PagosDTO> findById(int id);
    public PagosDTO save(PagosDTO pagos);
    public void deleteById(int id);

}
