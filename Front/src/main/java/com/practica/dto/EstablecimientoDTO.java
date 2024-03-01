package com.practica.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstablecimientoDTO {
	
	private int id;
	
	private String nombre;
	
	private String rbd;
	
	private String contactonombre;
	
	private String contactocargo;
	
	private String contactoemail;
	
	private String direccion;
	
	private String contactotelefono;
	
	private int sostenedorId;
	
	

}
