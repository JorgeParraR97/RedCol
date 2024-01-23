package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.DTO.PagosDTO;



@Service
public interface IPagosService {
	
	List<PagosDTO> PagosFindAll();
	void PagosSave(PagosDTO pagos);
	void PagosDelete(int id);
	ResponseEntity<Optional<PagosDTO>> findById(int id);
	ResponseEntity<String> actualizarPagos(@PathVariable int id, @RequestBody PagosDTO pagosActualizado);

}
