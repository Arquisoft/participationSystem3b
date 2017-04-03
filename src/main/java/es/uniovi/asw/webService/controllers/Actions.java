package es.uniovi.asw.webService.controllers;

import java.util.List;

import org.springframework.ui.Model;

import es.uniovi.asw.business.Services;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public class Actions {
	
	public static void listarCategorias(Model model, Citizen c) throws BusinessException {
		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
		model.addAttribute("categorias", categorias);
		
		for (Categoria categoria: categorias) {
			Long idCategoria = categoria.getId();
			List<Sugerencia> sugerencias = Services.getSystemServices().findSugerenciasByCategory(idCategoria);
			model.addAttribute("sugerencias"+idCategoria, sugerencias);
		}
		
		List<Sugerencia> sugerencias = Services.getSystemServices().findSugerenciasByUserId(c.getId());
		model.addAttribute("sugerenciasUser", sugerencias);
	}

}
