package com.practica.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("sostenedor")
public class SostenedorPageController {

	@Autowired
	ISostenedorService sosServicio;

	@Autowired
	IEstablecimientoService estServicio;

	@Autowired
	ITarifaService tarServicio;

	@Autowired
	IPagosService pagServicio;

	@GetMapping("/cambiocontra")
	public String showcambioContra(Model model, HttpSession session) {

		Long sostenedorId = (Long) session.getAttribute("sostenedorId");
		if (sostenedorId != null) {

			model.addAttribute("sostenedorId", sostenedorId);
		} else {

			return "redirect:/login/admin";
		}

		List<SostenedorDTO> sostenedores = sosServicio.findAllREST();

		model.addAttribute("sostenedores", sostenedores);

		return "/SostenedorPage/cambiocontrasena";

	}
	
	@PostMapping("/asREST")
    public String actualizarSostenedor(@Valid SostenedorDTO sostenedorDTO, Model model) {
        try {
        	sosServicio.actualizarSostenedor(sostenedorDTO);
            // Si la actualización en el servicio es exitosa, redirige a la página adecuada
            return "redirect:/login/admin";
        } catch (Exception e) {
            // Manejar el error adecuadamente
            model.addAttribute("error", "Error al actualizar sostenedor");
            return "redirect:/sostenedor/cambiocontra";
        }
    }

	@GetMapping("/home")
	public String showLoginForm(Model model, HttpSession session) {
		Long sostenedorId = (Long) session.getAttribute("sostenedorId");

		if (sostenedorId != null) {

			model.addAttribute("sostenedorId", sostenedorId);
		} else {

			return "redirect:/login/admin";
		}
		List<SostenedorDTO> sostenedores = sosServicio.findAllREST();
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		model.addAttribute("sostenedores", sostenedores);
		model.addAttribute("establecimiento", establecimiento);
		System.out.println("El ID del sostenedor almacenado en la sesión es: " + sostenedorId);
		return "/SostenedorPage/mantenedor_establecimiento";
	}

	@GetMapping("cobro")
	public String cobro(Model model, HttpSession session) {
		Long sostenedorId = (Long) session.getAttribute("sostenedorId");

		if (sostenedorId != null) {

			model.addAttribute("sostenedorId", sostenedorId);
		} else {

			return "redirect:/login/admin";
		}
		List<TarifaDTO> tarifa = tarServicio.findAllREST();
		List<EstablecimientoDTO> establecimiento = estServicio.findAllREST();
		List<MapamensualidadDTO> mapamensualidad = tarServicio.findmmAllREST();
		List<SostenedorDTO> sostenedor = sosServicio.findAllREST();

		model.addAttribute("sostenedor", sostenedor);
		model.addAttribute("tarifa", tarifa);
		model.addAttribute("establecimiento", establecimiento);
		model.addAttribute("mapamensualidad", mapamensualidad);
		System.out.println("El ID del sostenedor almacenado en la sesión es: " + sostenedorId);
		return "/SostenedorPage/mantenedor_cobro";
	}

	@GetMapping("pagos")
	public String pagos(Model model, HttpSession session) {
		Long sostenedorId = (Long) session.getAttribute("sostenedorId");

		if (sostenedorId != null) {

			model.addAttribute("sostenedorId", sostenedorId);
		} else {

			return "redirect:/login/admin";
		}

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

		return "/SostenedorPage/mantenedor_pagos";
	}

}
