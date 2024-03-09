package com.microservice.bff.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDTO {
	
	
	private int id;
	
	private int periodo;
	
	
	private String descripcion;

	
	private int monto;
	
}