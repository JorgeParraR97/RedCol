package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.Client.ColegioClient;
import com.microservice.bff.DTO.ColegioDTO;


@Service
public class ColegioServiceImpl implements IColegioService {
	
	@Autowired
	ColegioClient colegioClient;

	@Override
	public List<ColegioDTO> ColegioFindAll() {
		return colegioClient.findAll();

	}

	@Override
	public void ColegioSave(ColegioDTO colegio) {
		colegioClient.save(colegio);
		
	}



	public ResponseEntity<Optional<ColegioDTO>> findById(int id) {
        if (id > 0 ) {
            return colegioClient.findById(id);
        } else {
            // Manejo del caso en que id no está presente
            // Puedes devolver una respuesta adecuada, por ejemplo, ResponseEntity.badRequest()
            return ResponseEntity.badRequest().build();
        }
    }
	
	@Override
    public ResponseEntity<String> actualizarColegio(int id, ColegioDTO colegioActualizado) {
        
        ResponseEntity<String> responseEntity = colegioClient.actualizarColegio(id, colegioActualizado);
        return responseEntity;
    }

	@Override
	public ResponseEntity<List<ColegioDTO>> findBySostenedorId(int sostenedorId) {
	    try {
	        ResponseEntity<List<ColegioDTO>> response = colegioClient.findBySostenedorId(sostenedorId);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            List<ColegioDTO> colegios = response.getBody();
	            return new ResponseEntity<>(colegios, HttpStatus.OK);
	        } else {
	            return response;
	        }
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	

}