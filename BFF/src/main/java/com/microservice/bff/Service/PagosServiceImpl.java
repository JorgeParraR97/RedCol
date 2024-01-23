package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.bff.Client.PagosClient;
import com.microservice.bff.DTO.PagosDTO;


@Service
public class PagosServiceImpl implements IPagosService {
	
	@Autowired
	PagosClient pagosClient;

	@Override
	public List<PagosDTO> PagosFindAll() {
		return pagosClient.findAll();

	}

	@Override
	public void PagosSave(PagosDTO pagos) {
		pagosClient.save(pagos);
		
	}
	
	public ResponseEntity<Optional<PagosDTO>> findById(int id) {
        if (id > 0 ) {
            return pagosClient.findById(id);
        } else {
            
            return ResponseEntity.badRequest().build();
        }
    }
	
	@Override
    public ResponseEntity<String> actualizarPagos(int id, PagosDTO pagosActualizado) {
        
        ResponseEntity<String> responseEntity = pagosClient.actualizarPagos(id, pagosActualizado);
        return responseEntity;
    }
	
	
	@Override
	public void PagosDelete(int id) {
		pagosClient.delete(id);
	}

}
