package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import es.uniovi.asw.business.Services;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;

public class CitizenServiceTest {

	@Test
	public void addDeleteFindVoteCommentTest() throws BusinessException {

		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
		Citizen citizen = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
		
		Comentario com = new Comentario(citizen, sugerencias.get(0), "comentario de prueba2");
		
		assertEquals(citizen,com.getCitizen());
		assertNull(Services.getCitizenServices().findComentarioById(com.getId()));
		Services.getCitizenServices().addComentario(com);
		assertEquals(com, Services.getCitizenServices().findComentarioById(com.getId()));
		assertEquals(0, com.getVotosTotal());
		VotoComentario vc = new VotoComentario(com,citizen,true);
		Services.getCitizenServices().voteComentario(vc);
		assertEquals(1, com.getVotosTotal());
		Services.getCitizenServices().deleteComentario(com.getId());

		com.setContenido("hola");
		assertEquals(com.getContenido(),"hola");
		
		
		assertNull(Services.getCitizenServices().findComentarioById(com.getId()));
	}
	
	@Test
	public void addDeleteVoteUpdateSugerencia() throws BusinessException{
		Citizen citizen = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
		List<String> palabras = new ArrayList<String>();
		palabras.add("qweqweqw");
		Categoria catnueva = new Categoria("test",new Date(), new Date(), 5, palabras);
		Services.getAdminServices().addCategoria(catnueva);
		
		Sugerencia sug = new Sugerencia(citizen, "test","sugerencia de prueba",catnueva);
		
		assertNull(Services.getSystemServices().findSugerenciaById(sug.getId()));
		
		Services.getCitizenServices().addSugerencia(sug);
		
		assertEquals(sug, Services.getSystemServices().findSugerenciaById(sug.getId()));
		
		assertEquals(0, sug.getVotosTotal());
		
		VotoSugerencia vs = new VotoSugerencia(sug,citizen,true);
		
		Services.getCitizenServices().voteSugerencia(vs);
		
		assertEquals(1, sug.getVotosTotal());
	
		Services.getAdminServices().deleteCategoria(catnueva.getId());
		
		Sugerencia s = Services.getSystemServices().findSugerenciaById(sug.getId()); //Cojo la sugerencia actualizada
		assertEquals(1L, s.getCategoria().getId());
		
		Services.getCitizenServices().deleteSugerencia(sug.getId());
		
		assertNull(Services.getSystemServices().findSugerenciaById(sug.getId()));
	}
	
	@Test
	public void testPalabras() throws BusinessException{
		Citizen citizen = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
		List<String> palabras = new ArrayList<String>();
		palabras.add("test");
		palabras.add("damn");
		Categoria catnueva = new Categoria("test",new Date(), new Date(), 5, palabras);
		Services.getAdminServices().addCategoria(catnueva);
		
		Sugerencia sug = new Sugerencia(citizen, "test palabras","sugerencia de prueba de damn test",catnueva);
		
		Services.getCitizenServices().addSugerencia(sug);
		
		Sugerencia s = Services.getSystemServices().findSugerenciaById(sug.getId());
		
		assertEquals("**** palabras",s.getTitulo());
		assertEquals("sugerencia de prueba de **** ****",s.getContenido());
		assertEquals(SugerenciaStatus.Anulada,s.getEstado());
		
		s.setTitulo("fixed titulo");
		s.setContenido("fixed contenido");
		
		Services.getCitizenServices().updateSugerencia(s);
		Sugerencia k = Services.getSystemServices().findSugerenciaById(sug.getId());
		assertEquals(SugerenciaStatus.EnVotacion,k.getEstado());
	}

}
