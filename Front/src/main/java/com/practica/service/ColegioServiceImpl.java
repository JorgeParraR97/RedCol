package com.practica.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.dto.ColegioDTO;
import com.practica.dto.SostenedorDTO;


@Service
public class ColegioServiceImpl implements IColegioService {
	
	@Autowired
    private RestTemplate restTemplate;

	@Override
	public List<ColegioDTO> findAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<ColegioDTO> colegios = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:8080/api/bff/colegio/findAll"), ColegioDTO[].class));
			return colegios;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResponseEntity<String> saveREST(ColegioDTO c) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<ColegioDTO> requestEntity = new HttpEntity<>(c, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:8080/api/bff/colegio/create", requestEntity, String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            String responseBody = responseEntity.getBody();
	            return ResponseEntity.ok(responseBody);
	        } else {
	            System.out.println("Ha ocurrido un error");
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación del colegio");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}

	@Override
	public void actualizarColegio(ColegioDTO colegioDTO) {
        HttpHeaders headers = new HttpHeaders();
       
        HttpEntity<ColegioDTO> requestEntity = new HttpEntity<>(colegioDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		"http://localhost:8080/api/bff/colegio/actualizar/{id}",
                HttpMethod.PUT,
                requestEntity,
                String.class,
                colegioDTO.getId() 
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar colegio en el BFF");
        }
    }

	@Override
	public ColegioDTO deleteREST(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ColegioDTO> responseEntity = restTemplate
					.getForEntity("http://localhost:8080/api/bff/colegio/buscar" + "/" + id, ColegioDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				ColegioDTO dto = responseEntity.getBody();

				restTemplate.delete("http://localhost:8080/api/bff/colegio/delete" + "/" + id);

				return dto;
			} else {
				System.out.println("A ocurrido un error");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
