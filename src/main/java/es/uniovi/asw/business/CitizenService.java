package es.uniovi.asw.business;

import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotosComentarios;
import es.uniovi.asw.model.VotosSugerencias;

public interface CitizenService {
	
	public void addSugerencia(Sugerencia sugerencia);
	
	public void addComentario(Comentario comentario);
	
	public void updateSugerencia(Sugerencia sugerencia);
	
	public void deleteSugerencia(Long id);
	
	public void deleteComentario(Long id);
	
	public void voteSugerencia(VotosSugerencias voto);
	
	public void voteComentario(VotosComentarios voto);

}
