package com.practica.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagosDTO {
	
	private int id;
	
	private String numerodocumento;
	
	private int tipopago;
	
	private int monto;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecharegistro;
	
	private int entidadId;
	
	private int periodo;
	
	private String mes;
	
	private int pagado;
	
	private int saldo;
	
	
}
