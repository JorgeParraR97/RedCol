package com.microservice.bff.DTO;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColegioDTO {
	
	private int id;
	
	
	private String nombre;
	
	
	private String RBD;
	
	
	private String email;
	
	
	private String direccion;
	
	
	private String telefono;
	
	

}