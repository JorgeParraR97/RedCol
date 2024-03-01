package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.PagosEntity;


@Repository
public interface PagosRepository extends JpaRepository<PagosEntity, Integer> {

}
