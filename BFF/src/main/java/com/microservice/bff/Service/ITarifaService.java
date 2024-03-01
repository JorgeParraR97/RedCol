package com.microservice.bff.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.DTO.TarifaDTO;


@Service
public interface ITarifaService {
	
	List<TarifaDTO> TarifaFindAll();
	void TarifaSave(TarifaDTO tarifa);
	void TarifaDelete(int id);
	ResponseEntity<Optional<TarifaDTO>> findById(int id);
	ResponseEntity<String> actualizarTarifa(@PathVariable int id, @RequestBody TarifaDTO tarifaActualizado);

}