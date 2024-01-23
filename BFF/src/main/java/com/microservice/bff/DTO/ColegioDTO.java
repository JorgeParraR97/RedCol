package com.microservice.bff.DTO;



import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColegioDTO {
	
	private int id;
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("rbd")
	private String rbd;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("direccion")
	private String direccion;
	
	@JsonProperty("telefono")
	private String telefono;
	
	
	@JsonProperty("sostenedorId")
	private int sostenedorId;

}