package com.practica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "establecimiento")
public class EstablecimientoEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String nombre;
	
	
	private String rbd;
	
	private String contactonombre;
	
	private String contactoemail;
	
	private String contactocargo;
	
	
	private String direccion;
	
	
	private String contactotelefono;
	
	private int sostenedorId;

}