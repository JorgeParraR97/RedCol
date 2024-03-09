package com.practica.dto;

import java.util.Date;

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
	
	private Date fecharegistro;
	
	private int entidadId;
	
	private int periodo;
	
	private String mes;
	
	private int pagado;
	
	private int saldo;
	
	
}
