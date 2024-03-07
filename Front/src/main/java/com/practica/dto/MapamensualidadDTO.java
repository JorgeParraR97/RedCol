package com.practica.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MapamensualidadDTO {
	
	
	private int id;
	
	private String mes;
	
	private int periodo;
	
	private int tarifaId;
	
	private int sostenedorId;
	
	private int establecimientoId;
	
	
}