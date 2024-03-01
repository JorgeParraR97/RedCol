package com.practica.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.EstablecimientoEntity;



@Repository
public interface EstablecimientoRepository extends CrudRepository<EstablecimientoEntity, Integer> {
	
	
	List<EstablecimientoEntity> findBySostenedorId(int sostenedorId);

}