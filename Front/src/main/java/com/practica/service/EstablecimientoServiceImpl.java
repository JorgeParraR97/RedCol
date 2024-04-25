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
import com.practica.dto.EstablecimientoDTO;




@Service
public class EstablecimientoServiceImpl implements IEstablecimientoService {
	
	@Autowired
    private RestTemplate restTemplate;

	@Override
	public List<EstablecimientoDTO> findAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<EstablecimientoDTO> establecimientos = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:7778/api/bff/establecimiento/findAll"), EstablecimientoDTO[].class));
			return establecimientos;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResponseEntity<String> saveREST(EstablecimientoDTO e) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<EstablecimientoDTO> requestEntity = new HttpEntity<>(e, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:7778/api/bff/establecimiento/create", requestEntity, String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            String responseBody = responseEntity.getBody();
	            return ResponseEntity.ok(responseBody);
	        } else {
	            System.out.println("Ha ocurrido un error");
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación del establecimiento");
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}

	@Override
	public void actualizarEstablecimiento(EstablecimientoDTO establecimientoDTO) {
        HttpHeaders headers = new HttpHeaders();
       
        HttpEntity<EstablecimientoDTO> requestEntity = new HttpEntity<>(establecimientoDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		"http://localhost:7778/api/bff/establecimiento/actualizar/{id}",
                HttpMethod.PUT,
                requestEntity,
                String.class,
                establecimientoDTO.getId() 
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar establecimiento en el BFF");
        }
    }

	@Override
	public EstablecimientoDTO deleteREST(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<EstablecimientoDTO> responseEntity = restTemplate
					.getForEntity("http://localhost:7778/api/bff/establecimiento/buscar" + "/" + id, EstablecimientoDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				EstablecimientoDTO dto = responseEntity.getBody();

				restTemplate.delete("http://localhost:7778/api/bff/establecimiento/delete" + "/" + id);

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
