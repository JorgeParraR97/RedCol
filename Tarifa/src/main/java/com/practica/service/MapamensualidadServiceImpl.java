package com.practica.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practica.dto.MapamensualidadDTO;
import com.practica.entity.MapamensualidadEntity;
import com.practica.repository.MapamensualidadRepository;


@Service
public class MapamensualidadServiceImpl implements IMapamensualidadService {
	
	
	@Autowired
	private MapamensualidadRepository data;
	
	
	public MapamensualidadEntity mapamensualidadDTO2Entity(MapamensualidadDTO mmDTO) {
		MapamensualidadEntity mmEntity = new MapamensualidadEntity();
		mmEntity.setId(mmDTO.getId());
		mmEntity.setMes(mmDTO.getMes());
		mmEntity.setPeriodo(mmDTO.getPeriodo());
		mmEntity.setEstablecimientoId(mmDTO.getEstablecimientoId());
		mmEntity.setSostenedorId(mmDTO.getSostenedorId());
		mmEntity.setTarifaId(mmDTO.getTarifaId());
		return mmEntity;
	}

	public MapamensualidadDTO mapamensualidadEntity2DTO(MapamensualidadEntity mmE) {

		MapamensualidadDTO mmDTO = new MapamensualidadDTO();
		mmDTO.setId(mmE.getId());
		mmDTO.setMes(mmE.getMes());
		mmDTO.setPeriodo(mmE.getPeriodo());
		mmDTO.setEstablecimientoId(mmE.getEstablecimientoId());
		mmDTO.setSostenedorId(mmE.getSostenedorId());
		mmDTO.setTarifaId(mmE.getTarifaId());
		return mmDTO;
	}
	
	
	@Override
	public List<MapamensualidadDTO> findAll() {
		List<MapamensualidadDTO> lmmDTO = new ArrayList<MapamensualidadDTO>();

		Iterable<MapamensualidadEntity> iMME = data.findAll();

		Iterator<MapamensualidadEntity> imm = iMME.iterator();

		while (imm.hasNext()) {
			MapamensualidadEntity mmE = imm.next();
			MapamensualidadDTO mmDTO = mapamensualidadEntity2DTO(mmE);
			lmmDTO.add(mmDTO);
		}
		return lmmDTO;
	}
	
	@Override
	public Optional<MapamensualidadDTO> findById(int id) {
		Optional<MapamensualidadEntity> oMME = data.findById(id);

		if (oMME.isPresent()) {
			MapamensualidadEntity mmE = oMME.get();

			MapamensualidadDTO mmDTO = mapamensualidadEntity2DTO(mmE);

			Optional<MapamensualidadDTO> ommDTO = Optional.ofNullable(mmDTO);

			return ommDTO;
		} else {

			return Optional.empty();
		}

	}
	
	@Override
	public MapamensualidadDTO save(MapamensualidadDTO mm) {
		MapamensualidadEntity mmE = mapamensualidadDTO2Entity(mm);
		mmE = data.save(mmE);
		MapamensualidadDTO mmDTO = this.mapamensualidadEntity2DTO(mmE);
		return mmDTO;
	}
	
	
	@Override
	public void deleteById(int id) {
		data.deleteById(id);

	}
	
}
