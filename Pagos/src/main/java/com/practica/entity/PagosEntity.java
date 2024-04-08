package com.practica.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagos")
public class PagosEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String numerodocumento;
	
	private int tipopago;
	
	private int monto;
	
	private Date fecharegistro;
	
	private Date fecharegistrodocumento;
	
	private int entidadId;
	
	private int periodo;
	
	private String mes;
	
	private int pagado;
	
	private int saldo;
	
	

	

}