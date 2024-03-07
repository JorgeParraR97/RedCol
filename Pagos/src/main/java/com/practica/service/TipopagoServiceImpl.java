package com.practica.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.dto.TipopagoDTO;
import com.practica.entity.TipopagoEntity;
import com.practica.repository.TipopagoRepository;

@Service
public class TipopagoServiceImpl implements ITipopagoService {
	
	
	@Autowired
	private TipopagoRepository data;
	
	
	public TipopagoEntity tipopagoDTO2Entity(TipopagoDTO tpDTO) {
		TipopagoEntity tpEntity = new TipopagoEntity();
		tpEntity.setId(tpDTO.getId());
		tpEntity.setDescripcion(tpDTO.getDescripcion());

		return tpEntity;
	}

	public TipopagoDTO tipopagoEntity2DTO(TipopagoEntity tpE) {

		TipopagoDTO tpDTO = new TipopagoDTO();
		tpDTO.setId(tpE.getId());
		tpDTO.setDescripcion(tpE.getDescripcion());
	
		return tpDTO;
	}
	
	
	@Override
	public List<TipopagoDTO> findAll() {
		List<TipopagoDTO> ltpDTO = new ArrayList<TipopagoDTO>();

		Iterable<TipopagoEntity> itTPE = data.findAll();

		Iterator<TipopagoEntity> it = itTPE.iterator();

		while (it.hasNext()) {
			TipopagoEntity tpE = it.next();
			TipopagoDTO tpDTO = tipopagoEntity2DTO(tpE);
			ltpDTO.add(tpDTO);
		}
		return ltpDTO;
	}
	
}