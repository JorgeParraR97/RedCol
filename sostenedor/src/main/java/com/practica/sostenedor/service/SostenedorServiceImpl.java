package com.practica.sostenedor.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practica.sostenedor.dto.ColegioDTO;

import com.practica.sostenedor.dto.SostenedorDTO;
import com.practica.sostenedor.entity.SostenedorEntity;
import com.practica.sostenedor.repository.SostenedorRepository;

@Service
public class SostenedorServiceImpl implements ISostenedorService {

	@Autowired
	private SostenedorRepository data;

	@Autowired
	RestTemplate restTemplate;

	public SostenedorEntity sostenedorDTO2Entity(SostenedorDTO sDTO) {
		SostenedorEntity sEntity = new SostenedorEntity();
		sEntity.setId(sDTO.getId());
		sEntity.setNombre(sDTO.getNombre());
		sEntity.setEmail(sDTO.getEmail());
		sEntity.setContra(sDTO.getContra());
		sEntity.setRut(sDTO.getRut());
		sEntity.setRepresentante(sDTO.getRepresentante());
		return sEntity;
	}

	public SostenedorDTO sostenedorEntity2DTO(SostenedorEntity sE) {

		SostenedorDTO sDTO = new SostenedorDTO();
		sDTO.setId(sE.getId());
		sDTO.setNombre(sE.getNombre());
		sDTO.setEmail(sE.getEmail());
		sDTO.setContra(sE.getContra());
		sDTO.setRepresentante(sE.getRepresentante());
		sDTO.setRut(sE.getRut());
		return sDTO;
	}

	@Override
	public List<SostenedorDTO> findAll() {
		List<SostenedorDTO> lsDTO = new ArrayList<SostenedorDTO>();

		Iterable<SostenedorEntity> itSE = data.findAll();

		Iterator<SostenedorEntity> it = itSE.iterator();

		while (it.hasNext()) {
			SostenedorEntity sE = it.next();
			SostenedorDTO sDTO = sostenedorEntity2DTO(sE);
			lsDTO.add(sDTO);
		}
		return lsDTO;
	}

	@Override
	public Optional<SostenedorDTO> findById(int id) {
		Optional<SostenedorEntity> oSE = data.findById(id);

		if (oSE.isPresent()) {
			SostenedorEntity sE = oSE.get();

			SostenedorDTO sDTO = sostenedorEntity2DTO(sE);

			Optional<SostenedorDTO> osDTO = Optional.ofNullable(sDTO);

			return osDTO;
		} else {

			return Optional.empty();
		}

	}

	@Override
	public SostenedorDTO save(SostenedorDTO s) {
		SostenedorEntity sE = sostenedorDTO2Entity(s);
		sE = data.save(sE);
		SostenedorDTO sDTO = this.sostenedorEntity2DTO(sE);
		return sDTO;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);

	}

	public List<ColegioDTO> getColegios(int sostenedorId) {
        ResponseEntity<List<ColegioDTO>> responseEntity = restTemplate.exchange(
            "http://localhost:8080/api/bff/colegio/ls/" + sostenedorId,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ColegioDTO>>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            // Manejar el error de acuerdo a tus necesidades
            throw new RuntimeException("Error al obtener la lista de colegios. Código de estado: " + responseEntity.getStatusCodeValue());
        }
    }
	
    public Optional<SostenedorDTO> findByEmailAndContra(String email, String contra) {
        Optional<SostenedorEntity> optionalSostenedorEntity = data.findByEmail(email);

        // Verifica si el sostenedor existe y si la contraseña coincide
        return optionalSostenedorEntity.map(sostenedorEntity -> {
            if (sostenedorEntity.getContra().equals(contra)) {
                SostenedorDTO sostenedorDTO = sostenedorEntity2DTO(sostenedorEntity);
                return Optional.ofNullable(sostenedorDTO);
            } else {
                return Optional.<SostenedorDTO>empty();
            }
        }).orElse(Optional.empty());
    }

    // Otros métodos y lógica del servicio

	
	
    public Optional<SostenedorEntity> findByEmail(String email) {
        return data.findByEmail(email);
    }


}
