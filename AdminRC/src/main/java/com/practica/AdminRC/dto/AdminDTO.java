package com.practica.AdminRC.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	

    private int id;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("rut")
    private String rut;

    @JsonProperty("email")
    private String email;

    @JsonProperty("contra")
    private String contra;

    @JsonProperty("telefono")
    private String telefono;
    
    

}
