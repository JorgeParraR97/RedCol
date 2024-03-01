package com.practica.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.DTO.EstablecimientoDTO;
import com.practica.entity.EstablecimientoEntity;
import com.practica.repository.EstablecimientoRepository;



@Service
public class EstablecimientoServiceImpl implements IEstablecimientoService {
	
	@Autowired
    private EstablecimientoRepository data;
	
    public EstablecimientoEntity establecimientoDTO2Entity(EstablecimientoDTO eDTO)
    {
    	EstablecimientoEntity eEntity = new EstablecimientoEntity();
    	eEntity.setId(eDTO.getId());
    	eEntity.setNombre(eDTO.getNombre());
    	eEntity.setContactoemail(eDTO.getContactoemail());
    	eEntity.setContactonombre(eDTO.getContactonombre());
    	eEntity.setContactocargo(eDTO.getContactocargo());
    	eEntity.setContactotelefono(eDTO.getContactotelefono());
    	eEntity.setDireccion(eDTO.getDireccion());
    	eEntity.setRbd(eDTO.getRbd());
    	eEntity.setSostenedorId(eDTO.getSostenedorId());
    	return eEntity;
    }
    
    public EstablecimientoDTO establecimientoEntity2DTO(EstablecimientoEntity eE)
    {
   
    	EstablecimientoDTO eDTO = new EstablecimientoDTO();
    	eDTO.setId(eE.getId());
    	eDTO.setNombre(eE.getNombre());
    	eDTO.setContactoemail(eE.getContactoemail());
    	eDTO.setContactocargo(eE.getContactocargo());
    	eDTO.setContactonombre(eE.getContactonombre());
    	eDTO.setContactotelefono(eE.getContactotelefono());
    	eDTO.setRbd(eE.getRbd());
    	eDTO.setDireccion(eE.getDireccion());
    	eDTO.setSostenedorId(eE.getSostenedorId());
    	return eDTO;
    }
	
	
	

    @Override
    public List<EstablecimientoDTO> findAll() {
    	List<EstablecimientoDTO> leDTO = new ArrayList<EstablecimientoDTO>();
    	
    	Iterable<EstablecimientoEntity> itEE = data.findAll();
    	
    	Iterator<EstablecimientoEntity> it = itEE.iterator();
    	
    	while (it.hasNext())
    	{
    		EstablecimientoEntity eE = it.next();
    		EstablecimientoDTO eDTO = establecimientoEntity2DTO(eE);
    		leDTO.add(eDTO);
    	}
        return leDTO;
    }

    @Override
    public Optional<EstablecimientoDTO> findById(int id) {
    	Optional<EstablecimientoEntity> oEE = data.findById(id);
    	
    	if (oEE.isPresent())
    	{
    		EstablecimientoEntity eE = oEE.get();
    		
    		EstablecimientoDTO eDTO = establecimientoEntity2DTO(eE);
        
    		Optional<EstablecimientoDTO> oeDTO = Optional.ofNullable(eDTO);
        	
    		return oeDTO;
    	}
    	else
    	{
    		
            return Optional.empty();    		
    	}
    	

    }

    @Override
    public EstablecimientoDTO save(EstablecimientoDTO e) {
    	EstablecimientoEntity eE = establecimientoDTO2Entity(e);
        eE = data.save(eE);
        EstablecimientoDTO eDTO = this.establecimientoEntity2DTO(eE);
        return eDTO;
    }

	@Override
	public void deleteById(int id) {
		data.deleteById(id);

	}

	@Override
	public List<EstablecimientoDTO> findBySostenedorId(int sostenedorId) {
	    List<EstablecimientoEntity> establecimientoEntities = data.findBySostenedorId(sostenedorId);
	    
	    List<EstablecimientoDTO> establecimientoDTOs = new ArrayList<>();

	    for (EstablecimientoEntity establecimientoEntity : establecimientoEntities) {
	    	EstablecimientoDTO establecimientoDTO = establecimientoEntity2DTO(establecimientoEntity);
	    	establecimientoDTOs.add(establecimientoDTO);
	    }

	    return establecimientoDTOs;

	}

}