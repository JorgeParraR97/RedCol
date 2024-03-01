package com.practica.dto;

import org.hibernate.type.DateType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagosDTO {
	
private int id;
	
	
	private String tipo;
	
	
	private String deudor;
	
	
	private String estado;
	
	
	private int monto;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private DateType vencimiento;

}
