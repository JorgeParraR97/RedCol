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
import com.practica.dto.PagosDTO;


@Service
public class PagosServiceImpl implements IPagosService {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Override
	public List<PagosDTO> findAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<PagosDTO> proyectos = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:8080/api/bff/pagos/findAll"), PagosDTO[].class));
			return proyectos;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResponseEntity<String> saveREST(PagosDTO p) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<PagosDTO> requestEntity = new HttpEntity<>(p, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:8080/api/bff/pagos/create", requestEntity, String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            String responseBody = responseEntity.getBody();
	            return ResponseEntity.ok(responseBody);
	        } else {
	            System.out.println("Ha ocurrido un error");
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación del pago");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	
	@Override
	public void actualizarPagos(PagosDTO pagosDTO) {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<PagosDTO> requestEntity = new HttpEntity<>(pagosDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		"http://localhost:8080/api/bff/pagos/actualizar/{id}",
                HttpMethod.PUT,
                requestEntity,
                String.class,
                pagosDTO.getId() 
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar pagos en el BFF");
        }
    }
	
	
	@Override
	public PagosDTO deleteREST(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<PagosDTO> responseEntity = restTemplate
					.getForEntity("http://localhost:8080/api/bff/pagos/buscar" + "/" + id, PagosDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				PagosDTO dto = responseEntity.getBody();

				restTemplate.delete("http://localhost:8080/api/bff/pagos/delete" + "/" + id);

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
