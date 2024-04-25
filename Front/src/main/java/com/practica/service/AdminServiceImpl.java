package com.practica.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practica.dto.AdminDTO;
import com.practica.dto.LoginDTO;



@Service
public class AdminServiceImpl implements IAdminService {
	
	@Override
	public LoginDTO loginREST(LoginDTO loginDTO) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTO, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<LoginDTO> responseEntity = restTemplate.postForEntity("http://localhost:7778/api/bff/admin/login",
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
	
	@Override
	public ResponseEntity<String> saveREST(AdminDTO a) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<AdminDTO> requestEntity = new HttpEntity<>(a, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:7778/api/bff/admin/create", requestEntity, String.class);

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
