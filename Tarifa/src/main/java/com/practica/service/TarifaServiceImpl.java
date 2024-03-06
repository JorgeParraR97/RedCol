package com.practica.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practica.dto.TarifaDTO;
import com.practica.entity.TarifaEntity;
import com.practica.repository.TarifaRepository;

@Service
public class TarifaServiceImpl implements ITarifaService {
	
	
	@Autowired
	private TarifaRepository data;
	
	
	public TarifaEntity tarifaDTO2Entity(TarifaDTO tDTO) {
		TarifaEntity tEntity = new TarifaEntity();
		tEntity.setId(tDTO.getId());
		tEntity.setDescripcion(tDTO.getDescripcion());
		tEntity.setMonto(tDTO.getMonto());
		return tEntity;
	}

	public TarifaDTO tarifaEntity2DTO(TarifaEntity tE) {

		TarifaDTO tDTO = new TarifaDTO();
		tDTO.setId(tE.getId());
		tDTO.setDescripcion(tE.getDescripcion());
		tDTO.setMonto(tE.getMonto());
		return tDTO;
	}
	
	
	@Override
	public List<TarifaDTO> findAll() {
		List<TarifaDTO> ltDTO = new ArrayList<TarifaDTO>();

		Iterable<TarifaEntity> itTE = data.findAll();

		Iterator<TarifaEntity> it = itTE.iterator();

		while (it.hasNext()) {
			TarifaEntity tE = it.next();
			TarifaDTO tDTO = tarifaEntity2DTO(tE);
			ltDTO.add(tDTO);
		}
		return ltDTO;
	}
	
	@Override
	public Optional<TarifaDTO> findById(int id) {
		Optional<TarifaEntity> oTE = data.findById(id);

		if (oTE.isPresent()) {
			TarifaEntity tE = oTE.get();

			TarifaDTO tDTO = tarifaEntity2DTO(tE);

			Optional<TarifaDTO> otDTO = Optional.ofNullable(tDTO);

			return otDTO;
		} else {

			return Optional.empty();
		}

	}
	
	@Override
	public TarifaDTO save(TarifaDTO t) {
		TarifaEntity tE = tarifaDTO2Entity(t);
		tE = data.save(tE);
		TarifaDTO tDTO = this.tarifaEntity2DTO(tE);
		return tDTO;
	}
	
	
	@Override
	public void deleteById(int id) {
		data.deleteById(id);

	}
	
}
