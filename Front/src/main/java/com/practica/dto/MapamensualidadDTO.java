package com.practica.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapamensualidadDTO {
	
	
	private int id;
	
	@JsonProperty("mes")
	private String mes;
	
	@JsonProperty("periodo")
	private int periodo;
	
	@JsonProperty("tarifaId")
	private int tarifaId;
	
	
	@JsonProperty("sostenedorId")
	private int sostenedorId;
	
	
	@JsonProperty("establecimientoId")
	private int establecimientoId;
	
	
}