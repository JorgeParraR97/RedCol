package com.practica.colegios.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColegioDTO {
	
	private int id;
	
	
	private String nombre;
	
	
	private String RBD;
	
	
	private String email;
	
	
	private String direccion;
	
	
	private String telefono;
	
	private int sostenedorId;

}
