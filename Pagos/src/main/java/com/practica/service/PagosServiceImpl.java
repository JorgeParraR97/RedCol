package com.practica.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.dto.PagosDTO;
import com.practica.entity.PagosEntity;
import com.practica.repository.PagosRepository;

@Service
public class PagosServiceImpl implements IPagosService {
	
	
	@Autowired
	private PagosRepository data;
	
	
	public PagosEntity pagosDTO2Entity(PagosDTO pDTO) {
		PagosEntity pEntity = new PagosEntity();
		pEntity.setId(pDTO.getId());
		pEntity.setTipo(pDTO.getTipo());
		pEntity.setDeudor(pDTO.getDeudor());
		pEntity.setEstado(pDTO.getEstado());
		pEntity.setMonto(pDTO.getMonto());
		pEntity.setVencimiento(pDTO.getVencimiento());
		return pEntity;
	}

	public PagosDTO pagosEntity2DTO(PagosEntity pE) {

		PagosDTO pDTO = new PagosDTO();
		pDTO.setId(pE.getId());
		pDTO.setTipo(pE.getTipo());
		pDTO.setDeudor(pE.getDeudor());
		pDTO.setEstado(pE.getEstado());
		pDTO.setMonto(pE.getMonto());
		pDTO.setVencimiento(pE.getVencimiento());
		return pDTO;
	}
	
	
	@Override
	public List<PagosDTO> findAll() {
		List<PagosDTO> lpDTO = new ArrayList<PagosDTO>();

		Iterable<PagosEntity> itPE = data.findAll();

		Iterator<PagosEntity> it = itPE.iterator();

		while (it.hasNext()) {
			PagosEntity pE = it.next();
			PagosDTO pDTO = pagosEntity2DTO(pE);
			lpDTO.add(pDTO);
		}
		return lpDTO;
	}
	
	@Override
	public Optional<PagosDTO> findById(int id) {
		Optional<PagosEntity> oPE = data.findById(id);

		if (oPE.isPresent()) {
			PagosEntity pE = oPE.get();

			PagosDTO pDTO = pagosEntity2DTO(pE);

			Optional<PagosDTO> opDTO = Optional.ofNullable(pDTO);

			return opDTO;
		} else {

			return Optional.empty();
		}

	}
	
	@Override
	public PagosDTO save(PagosDTO p) {
		PagosEntity pE = pagosDTO2Entity(p);
		pE = data.save(pE);
		PagosDTO pDTO = this.pagosEntity2DTO(pE);
		return pDTO;
	}
	
	
	@Override
	public void deleteById(int id) {
		data.deleteById(id);

	}

}
