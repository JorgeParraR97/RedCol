package com.practica.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.practica.dto.SostenedorDTO;


@Service
public class SostenedorServiceImpl implements ISostenedorService {
	
	
	@Override
	public List<SostenedorDTO> findAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<SostenedorDTO> proyectos = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:8080/api/bff/sostenedor/findAll"), SostenedorDTO[].class));
			return proyectos;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResponseEntity<String> saveREST(SostenedorDTO s) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<SostenedorDTO> requestEntity = new HttpEntity<>(s, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:8080/api/bff/sostenedor/create", requestEntity, String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            String responseBody = responseEntity.getBody();
	            return ResponseEntity.ok(responseBody);
	        } else {
	            System.out.println("Ha ocurrido un error");
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación del admin");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}

}
