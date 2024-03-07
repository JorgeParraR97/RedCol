package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.Client.TarifaClient;
import com.microservice.bff.DTO.MapamensualidadDTO;
import com.microservice.bff.DTO.TarifaDTO;


@Service
public class TarifaServiceImpl implements ITarifaService {
	
	@Autowired
	TarifaClient tarifaClient;

	@Override
	public List<TarifaDTO> TarifaFindAll() {
		return tarifaClient.findAll();

	}

	@Override
	public void TarifaSave(TarifaDTO tarifa) {
		tarifaClient.save(tarifa);
		
	}


	public ResponseEntity<Optional<TarifaDTO>> findById(int id) {
        if (id > 0 ) {
            return tarifaClient.findById(id);
        } else {
            // Manejo del caso en que id no está presente
            // Puedes devolver una respuesta adecuada, por ejemplo, ResponseEntity.badRequest()
            return ResponseEntity.badRequest().build();
        }
    }
	
	@Override
    public ResponseEntity<String> actualizarTarifa(int id, TarifaDTO tarifaActualizado) {
        
        ResponseEntity<String> responseEntity = tarifaClient.actualizarTarifa(id, tarifaActualizado);
        return responseEntity;
    }
	
	
	@Override
	public void TarifaDelete(int id) {
		tarifaClient.delete(id);
	}
	
	
	
	@Override
	public List<MapamensualidadDTO>MapaFindAll() {
		return tarifaClient.findmmAll();

	}

	@Override
	public void MapaSave(MapamensualidadDTO mapa) {
		tarifaClient.savemm(mapa);
		
	}


	public ResponseEntity<Optional<MapamensualidadDTO>> findmmById(int id) {
        if (id > 0 ) {
            return tarifaClient.findmmById(id);
        } else {
            // Manejo del caso en que id no está presente
            // Puedes devolver una respuesta adecuada, por ejemplo, ResponseEntity.badRequest()
            return ResponseEntity.badRequest().build();
        }
    }
	
	@Override
    public ResponseEntity<String> actualizarMapa(int id, MapamensualidadDTO mapaActualizado) {
        
        ResponseEntity<String> responseEntity = tarifaClient.actualizarMapa(id, mapaActualizado);
        return responseEntity;
    }
	
	
	@Override
	public void MapaDelete(int id) {
		tarifaClient.delete(id);
	}


	

}