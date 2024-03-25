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
import com.practica.dto.MapamensualidadDTO;
import com.practica.dto.TarifaDTO;

@Service
public class TarifaServiceImpl implements ITarifaService {
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	@Override
	public List<TarifaDTO> findAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<TarifaDTO> tarifas = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:8080/api/bff/tarifa/findAll"), TarifaDTO[].class));
			return tarifas;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResponseEntity<String> saveREST(TarifaDTO t) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<TarifaDTO> requestEntity = new HttpEntity<>(t, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:8080/api/bff/tarifa/create", requestEntity, String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            String responseBody = responseEntity.getBody();
	            return ResponseEntity.ok(responseBody);
	        } else {
	            System.out.println("Ha ocurrido un error");
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación de la tarifa");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	@Override
	public void actualizarTarifa(TarifaDTO tarifaDTO) {
        HttpHeaders headers = new HttpHeaders();
        // Puedes agregar headers si es necesario
        HttpEntity<TarifaDTO> requestEntity = new HttpEntity<>(tarifaDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		"http://localhost:8080/api/bff/tarifa/actualizar/{id}",
                HttpMethod.PUT,
                requestEntity,
                String.class,
                tarifaDTO.getId() // Reemplaza con el valor correcto
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar tarifa en el BFF");
        }
    }
	
	
	@Override
	public TarifaDTO deleteREST(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<TarifaDTO> responseEntity = restTemplate
					.getForEntity("http://localhost:8080/api/bff/tarifa/buscar" + "/" + id, TarifaDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				TarifaDTO dto = responseEntity.getBody();

				restTemplate.delete("http://localhost:8080/api/bff/tarifa/delete" + "/" + id);

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
	public List<MapamensualidadDTO> findmmAllREST() {
		try {
			ObjectMapper unMapper = new ObjectMapper();

			List<MapamensualidadDTO> mapas = Arrays
					.asList(unMapper.readValue(new URL("http://localhost:8080/api/bff/tarifa/findmmAll"), MapamensualidadDTO[].class));
			return mapas;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResponseEntity<String> savemmREST(MapamensualidadDTO mm) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<MapamensualidadDTO> requestEntity = new HttpEntity<>(mm, headers);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
	                "http://localhost:8080/api/bff/tarifa/createmm", requestEntity, String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            String responseBody = responseEntity.getBody();
	            return ResponseEntity.ok(responseBody);
	        } else {
	            System.out.println("Ha ocurrido un error");
	            return ResponseEntity.status(responseEntity.getStatusCode()).body("Error en la creación del mapa");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	    }
	}
	
	@Override
	public void actualizarMapa(MapamensualidadDTO mapamensualidadDTO) {
        HttpHeaders headers = new HttpHeaders();
        // Puedes agregar headers si es necesario
        HttpEntity<MapamensualidadDTO> requestEntity = new HttpEntity<>(mapamensualidadDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        		"http://localhost:8080/api/bff/tarifa/actualizarmm/{id}",
                HttpMethod.PUT,
                requestEntity,
                String.class,
                mapamensualidadDTO.getId() // Reemplaza con el valor correcto
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar mapa en el BFF");
        }
    }
	
	
	@Override
	public MapamensualidadDTO deletemmREST(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<MapamensualidadDTO> responseEntity = restTemplate
					.getForEntity("http://localhost:8080/api/bff/tarifa/buscarmm" + "/" + id, MapamensualidadDTO.class);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				MapamensualidadDTO dto = responseEntity.getBody();

				restTemplate.delete("http://localhost:8080/api/bff/tarifa/deletemm" + "/" + id);

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
	public MapamensualidadDTO findmmById(int id) {
	    try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<MapamensualidadDTO> responseEntity = restTemplate
	                .getForEntity("http://localhost:8080/api/bff/tarifa/buscarmm" + "/" + id, MapamensualidadDTO.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            return responseEntity.getBody();
	        } else {
	            System.out.println("Ha ocurrido un error al buscar el mapa");
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
}
	