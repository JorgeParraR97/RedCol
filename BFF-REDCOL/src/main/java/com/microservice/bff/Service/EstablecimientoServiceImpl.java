package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.Client.EstablecimientoClient;
import com.microservice.bff.DTO.EstablecimientoDTO;


@Service
public class EstablecimientoServiceImpl implements IEstablecimientoService {
	
	@Autowired
	EstablecimientoClient establecimientoClient;

	@Override
	public List<EstablecimientoDTO> EstablecimientoFindAll() {
		return establecimientoClient.findAll();

	}

	@Override
	public void EstablecimientoSave(EstablecimientoDTO establecimiento) {
		establecimientoClient.save(establecimiento);
		
	}



	public ResponseEntity<Optional<EstablecimientoDTO>> findById(int id) {
        if (id > 0 ) {
            return establecimientoClient.findById(id);
        } else {
            // Manejo del caso en que id no está presente
            // Puedes devolver una respuesta adecuada, por ejemplo, ResponseEntity.badRequest()
            return ResponseEntity.badRequest().build();
        }
    }
	
	@Override
    public ResponseEntity<String> actualizarEstablecimiento(int id, EstablecimientoDTO establecimientoActualizado) {
        
        ResponseEntity<String> responseEntity = establecimientoClient.actualizarEstablecimiento(id, establecimientoActualizado);
        return responseEntity;
    }

	@Override
	public ResponseEntity<List<EstablecimientoDTO>> findBySostenedorId(int sostenedorId) {
	    try {
	        ResponseEntity<List<EstablecimientoDTO>> response = establecimientoClient.findBySostenedorId(sostenedorId);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            List<EstablecimientoDTO> establecimientos = response.getBody();
	            return new ResponseEntity<>(establecimientos, HttpStatus.OK);
	        } else {
	            return response;
	        }
	    } catch (RuntimeException e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@Override
	public void EstablecimientoDelete(int id) {
		establecimientoClient.delete(id);
	}



	

}