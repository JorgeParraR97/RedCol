package com.microservice.bff.DTO;

import java.util.Date;

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
	
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("vencimiento")
	private Date vencimiento;

}
