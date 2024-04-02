package com.practica.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SostenedorDTO {
	
	
	private int id;
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("rut")
	private String rut;
	
	@JsonProperty("email")
	private String email;

	
	@JsonProperty("contrasena")
	private String contrasena;
	
	@JsonProperty("representante")
	private String representante;

}
