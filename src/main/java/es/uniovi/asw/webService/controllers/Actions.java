package es.uniovi.asw.webService.controllers;

import java.util.List;

import org.springframework.ui.Model;

import es.uniovi.asw.business.Services;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public class Actions {
	
	public static void listarSugerencias(Model model, Citizen c) throws BusinessException {
		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
		model.addAttribute("sugerencias", sugerencias);
		
		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
		model.addAttribute("categorias", categorias);
		
		if (c != null) {
			sugerencias = Services.getSystemServices().findSugerenciasByUserId(c.getId());
			model.addAttribute("sugerenciasUser", sugerencias);
		}
	}

}
