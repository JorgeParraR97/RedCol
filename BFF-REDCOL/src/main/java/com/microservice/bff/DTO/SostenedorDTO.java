package com.microservice.bff.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SostenedorDTO {
	
    private int id;
	private String nombre;
	private String rut;
	private String email;
	private String contrasena;
	private String representante;

}