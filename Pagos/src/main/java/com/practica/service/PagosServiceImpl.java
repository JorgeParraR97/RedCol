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
		pEntity.setNumerodocumento(pDTO.getNumerodocumento());
		pEntity.setTipopago(pDTO.getTipopago());
		pEntity.setMonto(pDTO.getMonto());
		pEntity.setFecharegistro(pDTO.getFecharegistro());
		pEntity.setEntidadId(pDTO.getEntidadId());
		pEntity.setPeriodo(pDTO.getPeriodo());
		pEntity.setMes(pDTO.getMes());
		pEntity.setPagado(pDTO.getPagado());
		pEntity.setFecharegistrodocumento(pDTO.getFecharegistrodocumento());		
		return pEntity;
	}

	public PagosDTO pagosEntity2DTO(PagosEntity pE) {

		PagosDTO pDTO = new PagosDTO();
		pDTO.setId(pE.getId());
		pDTO.setNumerodocumento(pE.getNumerodocumento());
		pDTO.setTipopago(pE.getTipopago());
		pDTO.setMonto(pE.getMonto());
		pDTO.setFecharegistro(pE.getFecharegistro());
		pDTO.setEntidadId(pE.getEntidadId());
		pDTO.setPeriodo(pE.getPeriodo());
		pDTO.setMes(pE.getMes());
		pDTO.setPagado(pE.getPagado());
		pDTO.setSaldo(pE.getSaldo());
		pDTO.setFecharegistrodocumento(pE.getFecharegistrodocumento());
		
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
