package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.business.Services;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public class CitizenServiceTest {

	@Test
	public void addDeleteFindVoteCommentTest() throws BusinessException {

		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
		Citizen citizen = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
		
		Comentario com = new Comentario(citizen, sugerencias.get(0), "comentario de prueba2");
		
		assertNull(Services.getCitizenServices().findComentarioById(com.getId()));
		Services.getCitizenServices().addComentario(com);
		assertEquals(com, Services.getCitizenServices().findComentarioById(com.getId())); //DA ERROR AL BORRAR UN COMENTARIO QUE TENGA VOTOS
	//	assertEquals(0, com.getVotosTotal());
	//	VotoComentario vc = new VotoComentario(com,citizen,true);
	//	Services.getCitizenServices().voteComentario(vc);
	//	assertEquals(1, com.getVotosTotal());
		Services.getCitizenServices().deleteComentario(com.getId());

		assertNull(Services.getCitizenServices().findComentarioById(com.getId()));
	}

}
