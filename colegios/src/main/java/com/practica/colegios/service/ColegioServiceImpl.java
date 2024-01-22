package com.practica.colegios.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.colegios.dto.ColegioDTO;
import com.practica.colegios.entity.ColegiosEntity;
import com.practica.colegios.repository.ColegioRepository;

@Service
public class ColegioServiceImpl implements IColegioService {
	
	@Autowired
    private ColegioRepository data;
	
    public ColegiosEntity colegioDTO2Entity(ColegioDTO cDTO)
    {
    	ColegiosEntity cEntity = new ColegiosEntity();
    	cEntity.setId(cDTO.getId());
    	cEntity.setNombre(cDTO.getNombre());
    	cEntity.setEmail(cDTO.getEmail());
    	cEntity.setTelefono(cDTO.getTelefono());
    	cEntity.setDireccion(cDTO.getDireccion());
    	cEntity.setRbd(cDTO.getRbd());
    	cEntity.setSostenedorId(cDTO.getSostenedorId());
    	return cEntity;
    }
    
    public ColegioDTO colegioEntity2DTO(ColegiosEntity cE)
    {
   
    	ColegioDTO cDTO = new ColegioDTO();
    	cDTO.setId(cE.getId());
    	cDTO.setNombre(cE.getNombre());
    	cDTO.setEmail(cE.getEmail());
    	cDTO.setTelefono(cE.getTelefono());
    	cDTO.setRbd(cE.getRbd());
    	cDTO.setDireccion(cE.getDireccion());
    	cDTO.setSostenedorId(cE.getSostenedorId());
    	return cDTO;
    }
	
	
	

    @Override
    public List<ColegioDTO> findAll() {
    	List<ColegioDTO> lcDTO = new ArrayList<ColegioDTO>();
    	
    	Iterable<ColegiosEntity> itCE = data.findAll();
    	
    	Iterator<ColegiosEntity> it = itCE.iterator();
    	
    	while (it.hasNext())
    	{
    		ColegiosEntity cE = it.next();
    		ColegioDTO cDTO = colegioEntity2DTO(cE);
    		lcDTO.add(cDTO);
    	}
        return lcDTO;
    }

    @Override
    public Optional<ColegioDTO> findById(int id) {
    	Optional<ColegiosEntity> oCE = data.findById(id);
    	
    	if (oCE.isPresent())
    	{
    		ColegiosEntity cE = oCE.get();
    		
    		ColegioDTO cDTO = colegioEntity2DTO(cE);
        
    		Optional<ColegioDTO> ocDTO = Optional.ofNullable(cDTO);
        	
    		return ocDTO;
    	}
    	else
    	{
    		
            return Optional.empty();    		
    	}
    	

    }

    @Override
    public ColegioDTO save(ColegioDTO c) {
    	ColegiosEntity cE = colegioDTO2Entity(c);
        cE = data.save(cE);
        ColegioDTO cDTO = this.colegioEntity2DTO(cE);
        return cDTO;
    }

	@Override
	public void deleteById(int id) {
		data.deleteById(id);

	}

	@Override
	public List<ColegioDTO> findBySostenedorId(int sostenedorId) {
	    List<ColegiosEntity> colegiosEntities = data.findBySostenedorId(sostenedorId);
	    
	    List<ColegioDTO> colegioDTOs = new ArrayList<>();

	    for (ColegiosEntity colegioEntity : colegiosEntities) {
	        ColegioDTO colegioDTO = colegioEntity2DTO(colegioEntity);
	        colegioDTOs.add(colegioDTO);
	    }

	    return colegioDTOs;

	}

}
