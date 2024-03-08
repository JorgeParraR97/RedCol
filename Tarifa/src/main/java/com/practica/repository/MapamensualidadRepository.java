package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.MapamensualidadEntity;



@Repository
public interface MapamensualidadRepository extends JpaRepository<MapamensualidadEntity, Integer> {

}
