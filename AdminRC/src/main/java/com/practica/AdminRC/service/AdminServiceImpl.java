package com.practica.AdminRC.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.AdminRC.dto.AdminDTO;
import com.practica.AdminRC.entity.AdminEntity;
import com.practica.AdminRC.repository.AdminRepository;



@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
    private AdminRepository data;
	
    public AdminEntity adminDTO2Entity(AdminDTO aDTO)
    {
    	AdminEntity aEntity = new AdminEntity();
    	aEntity.setId(aDTO.getId());
    	aEntity.setNombre(aDTO.getNombre());
    	aEntity.setEmail(aDTO.getEmail());
    	aEntity.setTelefono(aDTO.getTelefono());
    	aEntity.setContra(aDTO.getContra());
    	aEntity.setRut(aDTO.getRut());
    	return aEntity;
    }
    
    public AdminDTO adminEntity2DTO(AdminEntity aE)
    {
   
    	AdminDTO aDTO = new AdminDTO();
    	aDTO.setId(aE.getId());
    	aDTO.setNombre(aE.getNombre());
    	aDTO.setEmail(aE.getEmail());
    	aDTO.setTelefono(aE.getTelefono());
    	aDTO.setRut(aE.getRut());
    	aDTO.setContra(aE.getContra());
    	return aDTO;
    }
	
	
	

    @Override
    public List<AdminDTO> findAll() {
    	List<AdminDTO> laDTO = new ArrayList<AdminDTO>();
    	
    	Iterable<AdminEntity> itAE = data.findAll();
    	
    	Iterator<AdminEntity> it = itAE.iterator();
    	
    	while (it.hasNext())
    	{
    		AdminEntity aE = it.next();
    		AdminDTO aDTO = adminEntity2DTO(aE);
    		laDTO.add(aDTO);
    	}
        return laDTO;
    }

    @Override
    public Optional<AdminDTO> findById(int id) {
    	Optional<AdminEntity> oAE = data.findById(id);
    	
    	if (oAE.isPresent())
    	{
    		AdminEntity aE = oAE.get();
    		
    		AdminDTO aDTO = adminEntity2DTO(aE);
        
    		Optional<AdminDTO> oaDTO = Optional.ofNullable(aDTO);
        	
    		return oaDTO;
    	}
    	else
    	{
    		
            return Optional.empty();    		
    	}
    	

    }

    @Override
    public AdminDTO save(AdminDTO a) {
    	AdminEntity aE = adminDTO2Entity(a);
        aE = data.save(aE);
        AdminDTO aDTO = this.adminEntity2DTO(aE);
        return aDTO;
    }

	@Override
	public void delete(int id) {
		data.deleteById(id);
		
	}
	
    public Optional<AdminDTO> findByEmailAndContra(String email, String contra) {
        Optional<AdminEntity> optionalAdminEntity = data.findByEmail(email);

        // Verifica si el sostenedor existe y si la contraseña coincide
        return optionalAdminEntity.map(adminEntity -> {
            if (adminEntity.getContra().equals(contra)) {
                AdminDTO adminDTO = adminEntity2DTO(adminEntity);
                return Optional.ofNullable(adminDTO);
            } else {
                return Optional.<AdminDTO>empty();
            }
        }).orElse(Optional.empty());
    }

    // Otros métodos y lógica del servicio

	
	
    public Optional<AdminEntity> findByEmail(String email) {
        return data.findByEmail(email);
    }

}
