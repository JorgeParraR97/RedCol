package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.TarifaEntity;


@Repository
public interface TarifaRepository extends JpaRepository<TarifaEntity, Integer> {

}
