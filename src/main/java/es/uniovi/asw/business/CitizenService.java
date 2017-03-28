package es.uniovi.asw.business;

import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.VotoSugerencia;

public interface CitizenService {
	
	public void addSugerencia(Sugerencia sugerencia);
	
	public void addComentario(Comentario comentario);
	
	public void updateSugerencia(Sugerencia sugerencia);
	
	public void deleteSugerencia(Long id);
	
	public void deleteComentario(Long id);
	
	public void voteSugerencia(VotoSugerencia voto);
	
	public void voteComentario(VotoComentario voto);

}
