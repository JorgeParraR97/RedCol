package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.TipopagoEntity;

@Repository
public interface TipopagoRepository extends JpaRepository<TipopagoEntity, Integer> {

}
