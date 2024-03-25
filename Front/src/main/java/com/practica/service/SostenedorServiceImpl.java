package com.practica.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
import com.practica.dto.LoginDTO;
import com.practica.dto.SostenedorDTO;


@Service
public class SostenedorServiceImpl implements ISostenedorService {
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	@Override
	public List<SostenedorDTO> findAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<SostenedorDTO> sostenedor = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:8080/api/bff/sostenedor/findAll"), SostenedorDTO[].class));
			return sostenedor;

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
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación del sostenedor");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	@Override
	public void actualizarSostenedor(SostenedorDTO sostenedorDTO) {
        HttpHeaders headers = new HttpHeaders();
        // Puedes agregar headers si es necesario
        HttpEntity<SostenedorDTO> requestEntity = new HttpEntity<>(sostenedorDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		"http://localhost:8080/api/bff/sostenedor/actualizar/{id}",
                HttpMethod.PUT,
                requestEntity,
                String.class,
                sostenedorDTO.getId() // Reemplaza con el valor correcto
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar sostenedor en el BFF");
        }
    }
	
	
	@Override
	public SostenedorDTO deleteREST(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<SostenedorDTO> responseEntity = restTemplate
					.getForEntity("http://localhost:8080/api/bff/sostenedor/buscar" + "/" + id, SostenedorDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				SostenedorDTO dto = responseEntity.getBody();

				restTemplate.delete("http://localhost:8080/api/bff/sostenedor/delete" + "/" + id);

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
	
	
	@Override
    public List<EstablecimientoDTO> getEstablecimientos(int sostenedorId) {
        String url = "http://localhost:8080/api/bff/sostenedor/colegio/{sostenedorId}";
        ResponseEntity<List<EstablecimientoDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EstablecimientoDTO>>() {},
                sostenedorId
        );

        return responseEntity.getBody();
    }
	
	@Override
	public LoginDTO loginsosREST(LoginDTO loginDTO) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTO, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<LoginDTO> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/bff/sostenedor/login",
					requestEntity, LoginDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				LoginDTO dto = responseEntity.getBody();
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
