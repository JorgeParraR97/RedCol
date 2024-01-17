package com.practica.colegios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practica.colegios.dto.ColegioDTO;
import com.practica.colegios.service.IColegioService;



@RestController
@RequestMapping("api/colegio")
public class ColegioController {
	
	@Autowired
	private IColegioService servicio;
	
	
	
    @ResponseBody @PostMapping("/crear") //CREATE-POST
    public ColegioDTO agregarColegio( @NonNull @RequestBody ColegioDTO colegio) {
        return servicio.save(colegio);
    }
	
	
    @ResponseBody @GetMapping("/listar")
    public List<ColegioDTO> getAllColegio() {
    	List<ColegioDTO> l = servicio.findAll();
    	return l;
    }
    
    
    @GetMapping("/ls/{sostenedorId}")
    public ResponseEntity<List<ColegioDTO>> findBySostenedorId(@PathVariable int sostenedorId) {
        try {
            List<ColegioDTO> colegios = servicio.findBySostenedorId(sostenedorId);

            if (colegios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(colegios, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	

}