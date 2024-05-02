package com.practica.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practica.dto.EstablecimientoDTO;
import com.practica.dto.MapamensualidadDTO;
import com.practica.dto.PagosDTO;
import com.practica.dto.SostenedorDTO;
import com.practica.dto.TarifaDTO;
import com.practica.dto.TipopagoDTO;
import com.practica.service.IEstablecimientoService;
import com.practica.service.IPagosService;
import com.practica.service.ISostenedorService;
import com.practica.service.ITarifaService;

@Controller
@RequestMapping("admin")
public class AdminPageController {
	
	@Autowired
	ISostenedorService sosServicio;
	
	@Autowired
	IEstablecimientoService estServicio;
	
	@Autowired
	IPagosService pagServicio;
	
	@Autowired
	ITarifaService tarServicio;
	
	@GetMapping("sostenedor")
	public String sostenedor(Model model) {
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST(); 
		model.addAttribute("sostenedor", sostenedor);
		return "AdminPage/mantenedor_sostenedor";
	}
	
	
	@PostMapping("/gsREST")
	public String saveREST(@Valid SostenedorDTO sostenedorDTO, Model model) {
	    try {
	        ResponseEntity<String> response = sosServicio.saveREST(sostenedorDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/sostenedor";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear admin");
	            // Redirige a la página de registro
	            return "redirect:/admin/sostenedor"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/sostenedor"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	@PostMapping("/asREST")
    public String actualizarSostenedor(@Valid SostenedorDTO sostenedorDTO, Model model) {
        try {
        	sosServicio.actualizarSostenedor(sostenedorDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/sostenedor";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar sostenedor");
            return "redirect:/admin/sostenedor";
        }
    }
	
	@DeleteMapping("/bsREST/{id}")
	public ResponseEntity<String> deleteSostenedor(@PathVariable int id) {
	    try {
	        sosServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	@GetMapping("/esREST/{id}")
	@ResponseBody // Indica que el valor devuelto debe ser serializado como respuesta HTTP
	public ResponseEntity<List<EstablecimientoDTO>> getEstablecimientos(@PathVariable("id") int id) {
	    try {
	        List<EstablecimientoDTO> establecimientos = sosServicio.getEstablecimientos(id);
	        return new ResponseEntity<>(establecimientos, HttpStatus.OK);
	    } catch (Exception e) {
	        // Manejar errores
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("establecimiento")
	public String colegio(Model model) {
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();
		
		model.addAttribute("sostenedor", sostenedor);
		model.addAttribute("establecimiento", establecimiento);
		return "AdminPage/mantenedor_establecimiento";
	}
	
	
	@PostMapping("/geREST")
	public String saveREST(@Valid EstablecimientoDTO establecimientoDTO, Model model) {
	    try {
	        ResponseEntity<String> response = estServicio.saveREST(establecimientoDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/establecimiento";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear establecimiento");
	            // Redirige a la página de registro
	            return "redirect:/admin/establecimiento"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/establecimiento"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	@PostMapping("/aeREST")
    public String actualizarEstablecimiento(@Valid EstablecimientoDTO establecimientoDTO, Model model) {
        try {
        	estServicio.actualizarEstablecimiento(establecimientoDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/establecimiento";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar establecimiento");
            return "redirect:/admin/establecimiento";
        }
    }
	
	
	@DeleteMapping("/beREST/{id}")
	public ResponseEntity<String> deleteEstablecimiento(@PathVariable int id) {
	    try {
	        estServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	@GetMapping("pagos")
	public String pagos(Model model) {
		List<PagosDTO> pagos = pagServicio.findAllREST(); 
		List<TipopagoDTO> tipopago = pagServicio.findtpAllREST();
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();
		List<MapamensualidadDTO> mapa = tarServicio.findmmAllREST();
		List<MapamensualidadDTO> mapamensualidad = tarServicio.findmmAllREST();
		List<TarifaDTO> tarifa = tarServicio.findAllREST();
		
		
		
		model.addAttribute("tarifa", tarifa);
		model.addAttribute("mapa", mapa);
		model.addAttribute("pagos", pagos);
		model.addAttribute("establecimiento", establecimiento);
		model.addAttribute("sostenedor", sostenedor);
		model.addAttribute("tipopago", tipopago);
		model.addAttribute("mapamensualidad", mapamensualidad);
		
		return "AdminPage/mantenedor_pagos";
	}
	
	
    @GetMapping("/findSos")
    public List<SostenedorDTO> findAllSos() {
        return sosServicio.findAllREST();
    }
    
    @GetMapping("/findEst")
    public List<EstablecimientoDTO> findAllCol() {
        return estServicio.findAllREST();
    }
    
    @PostMapping("/gpREST")
	public String saveREST(@RequestBody PagosDTO pagosDTO, Model model) {
	    try {
	    	
	    	// Agregar un registro de la información recibida por consola
	        System.out.println("Datos recibidos en el controlador:");
	        System.out.println(pagosDTO.toString());
	        ResponseEntity<String> response = pagServicio.saveREST(pagosDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/pagos";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear pagos");
	            // Redirige a la página de registro
	            return "redirect:/admin/pagos"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/pagos"; // Cambiar según la ruta real de tu página de registro
	    }
	}
    
	@DeleteMapping("/bpREST/{id}")
	public ResponseEntity<String> deletePagos(@PathVariable int id) {
	    try {
	        pagServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
    
    
    @GetMapping("tarifa")
	public String tarifa(Model model) {
		List<TarifaDTO> tarifa = tarServicio.findAllREST();
		model.addAttribute("tarifa", tarifa);
		return "AdminPage/mantenedor_tarifa";
	}
	
	
	@PostMapping("/gtREST")
	public String saveREST(@Valid TarifaDTO tarifaDTO, Model model) {
	    try {
	        ResponseEntity<String> response = tarServicio.saveREST(tarifaDTO);

	        if (response != null && response.getStatusCode().is2xxSuccessful()) {
	            // Si la creación en el BffAdminController es exitosa, redirige a la página de login
	            return "redirect:/admin/tarifa";
	        } else {
	            // Creación fallida, agregar mensaje de error al modelo
	            model.addAttribute("error", "Error al crear tarifa");
	            // Redirige a la página de registro
	            return "redirect:/admin/tarifa"; // Cambiar según la ruta real de tu página de registro
	        }
	    } catch (Exception e) {
	        // Error interno, agregar mensaje de error al modelo
	        model.addAttribute("error", "Error interno del servidor");
	        // Redirige a la página de registro
	        return "redirect:/admin/tarifa"; // Cambiar según la ruta real de tu página de registro
	    }
	}
	
	@PostMapping("/atREST")
    public String actualizarTarifa(@Valid TarifaDTO tarifaDTO, Model model) {
        try {
        	System.out.println("Contenido de tarifaDTO: " + tarifaDTO);
        	tarServicio.actualizarTarifa(tarifaDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/tarifa";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar tarifa");
            return "redirect:/admin/tarifa";
        }
    }
	
	
	@DeleteMapping("/btREST/{id}")
	public ResponseEntity<String> deleteTarifa(@PathVariable int id) {
	    try {
	        tarServicio.deleteREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	
    @GetMapping("cobro")
	public String cobro(Model model) {
		List<TarifaDTO> tarifa = tarServicio.findAllREST();
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		List<MapamensualidadDTO> mapamensualidad = tarServicio.findmmAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();
		
		model.addAttribute("sostenedor" , sostenedor);
		model.addAttribute("tarifa", tarifa);
		model.addAttribute("establecimiento", establecimiento);
		model.addAttribute("mapamensualidad", mapamensualidad);
		return "AdminPage/mantenedor_cobro";
	}
    
    @PostMapping("/gmmREST")
    public String savemmREST(@RequestBody MapamensualidadDTO mapamensualidadDTO, Model model) {
        try {
            System.out.println("Datos recibidos en el controlador: " + mapamensualidadDTO.toString());
            
            // Verificar si el registro ya existe
            MapamensualidadDTO mapaExistente = tarServicio.findmmById(mapamensualidadDTO.getId());
            
            if (mapaExistente != null) {
                // Si el registro ya existe, actualizarlo
                tarServicio.actualizarMapa(mapamensualidadDTO);
            } else {
                // Si el registro no existe, guardarlo
                ResponseEntity<String> response = tarServicio.savemmREST(mapamensualidadDTO);
                
                if (response == null || !response.getStatusCode().is2xxSuccessful()) {
                    // Si hay un problema al guardar el nuevo registro, redirige con un mensaje de error
                    model.addAttribute("error", "Error al guardar Mapa");
                    return "redirect:/admin/cobro";
                }
            }

            // Redirige a la página adecuada después de guardar o actualizar el registro
            return "redirect:/admin/cobro";
            
        } catch (Exception e) {
            // Error interno, agregar mensaje de error al modelo y redirigir
            model.addAttribute("error", "Error interno del servidor");
            return "redirect:/admin/cobro";
        }
    }
    

	
	@PostMapping("/ammREST")
    public String actualizarMapa(@Valid MapamensualidadDTO mapamensualidadDTO, Model model) {
        try {
        	tarServicio.actualizarMapa(mapamensualidadDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/admin/cobro";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar Mapa");
            return "redirect:/admin/cobro";
        }
    }
	
	
	@DeleteMapping("/bmmREST/{id}")
	public ResponseEntity<String> deleteMapa(@PathVariable int id) {
	    try {
	        tarServicio.deletemmREST(id);
	        return ResponseEntity.ok("Eliminado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al intentar eliminar");
	    }
	}
	
	
	
}
