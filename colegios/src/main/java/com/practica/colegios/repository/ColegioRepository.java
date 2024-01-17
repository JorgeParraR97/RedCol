package com.practica.colegios.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.colegios.entity.ColegiosEntity;

@Repository
public interface ColegioRepository extends CrudRepository<ColegiosEntity, Integer> {
	
	
	List<ColegiosEntity> findBySostenedorId(int sostenedorId);

}
