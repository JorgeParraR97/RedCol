package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.Client.SostenedorClient;
import com.microservice.bff.DTO.EstablecimientoDTO;
import com.microservice.bff.DTO.SostenedorDTO;

@Service
public class SostenedorServiceImpl implements ISostenedorService {
	
	@Autowired
	SostenedorClient sostenedorClient;

	@Override
	public List<SostenedorDTO> SostenedorFindAll() {
		return sostenedorClient.findAll();

	}

	@Override
	public void SostenedorSave(SostenedorDTO sostenedor) {
		sostenedorClient.save(sostenedor);
		
	}

	@Override
	public ResponseEntity<List<EstablecimientoDTO>> getColegios(int sostenedorid) {
		ResponseEntity<List<EstablecimientoDTO>> responseEntity = sostenedorClient.getColegios(sostenedorid);
        return responseEntity;
	}

	public ResponseEntity<Optional<SostenedorDTO>> findById(int id) {
        if (id > 0 ) {
            return sostenedorClient.findById(id);
        } else {
            // Manejo del caso en que id no está presente
            // Puedes devolver una respuesta adecuada, por ejemplo, ResponseEntity.badRequest()
            return ResponseEntity.badRequest().build();
        }
    }
	
	@Override
    public ResponseEntity<String> actualizarSostenedor(int id, SostenedorDTO sostenedorActualizado) {
        
        ResponseEntity<String> responseEntity = sostenedorClient.actualizarSostenedor(id, sostenedorActualizado);
        return responseEntity;
    }
	
	
	@Override
	public void SostenedorDelete(int id) {
		sostenedorClient.delete(id);
	}


	

}
