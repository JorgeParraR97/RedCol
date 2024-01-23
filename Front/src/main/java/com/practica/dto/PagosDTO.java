package com.practica.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagosDTO {
	
    private int id;
	
    @JsonProperty("tipo")
	private String tipo;
	
    @JsonProperty("deudor")
	private String deudor;
	
    @JsonProperty("estado")
	private String estado;
	
    @JsonProperty("monto")
	private int monto;
	
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Santiago")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("vencimiento")
	private Date vencimiento;

}
