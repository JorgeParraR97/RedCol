package com.practica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.dto.MapamensualidadDTO;


@Service
public interface IMapamensualidadService {
	
	
	public List<MapamensualidadDTO> findAll();
    public Optional<MapamensualidadDTO> findById(int id);
    public MapamensualidadDTO save(MapamensualidadDTO mapa);
    public void deleteById(int id);

}
