package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.business.Services;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;

public class AdminService {
	
	@Test
	public void addDeleteCategoriaTest() throws BusinessException {
		int numCatAntes = Services.getSystemServices().findAllCategories().size();
		
		Categoria categoria = new Categoria("categoriaPrueba", new Date(), new Date(), 100);
		Services.getAdminServices().addCategoria(categoria);
		
		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
		int numCatDespues = categorias.size();
		
		assertTrue(categorias.contains(categoria));
		assertEquals(numCatAntes+1, numCatDespues);
		
		Services.getAdminServices().deleteCategoria(categoria.getId());
		categorias = Services.getSystemServices().findAllCategories();
		assertFalse(categorias.contains(categoria));
		
		categorias = Services.getSystemServices().findAllCategories();
		numCatDespues = categorias.size();
		assertEquals(numCatAntes, numCatDespues);
	}
	
	@Test
	public void anularSugerencia() throws BusinessException {
		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
		Sugerencia sugerencia = sugerencias.get(0);
		
		assertFalse(sugerencia.getEstado().equals(SugerenciaStatus.Anulada));
		
		Services.getAdminServices().anularSugerencia(sugerencia.getId());
		
		sugerencia = Services.getSystemServices().findSugerenciaById(sugerencia.getId());
		assertTrue(sugerencia.getEstado().equals(SugerenciaStatus.Anulada));
	}
	
	//@Test
	public void deleteSugerencia() throws BusinessException {
		Citizen citizen = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
		Categoria categoria = new Categoria("categoriaPrueba", new Date(), new Date(), 100);
		Services.getAdminServices().addCategoria(categoria);
		Sugerencia sugerencia = new Sugerencia(citizen,"prueba", "prueba", categoria);
		Services.getCitizenServices().addSugerencia(sugerencia);
		
		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
		int numSugerenciasAntes = sugerencias.size();
		
		Services.getAdminServices().deleteSugerencia(sugerencia.getId());
		
		sugerencias = Services.getSystemServices().findAllSugerencias();
		int numSugerenciasDespues = sugerencias.size();
		
		assertFalse(sugerencias.contains(sugerencia));
		assertEquals(numSugerenciasAntes-1, numSugerenciasDespues);
	}

}
