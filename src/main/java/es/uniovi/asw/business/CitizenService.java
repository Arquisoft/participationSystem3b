package es.uniovi.asw.business;

import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public interface CitizenService {
	
	public void addSugerencia(Sugerencia sugerencia) throws BusinessException;
	
	public void addComentario(Comentario comentario) throws BusinessException;
	
	public void updateSugerencia(Sugerencia sugerencia) throws BusinessException;
	
	public void deleteSugerencia(Long id) throws BusinessException;
	
	public void deleteComentario(Long id) throws BusinessException;
	
	public void voteSugerencia(VotoSugerencia voto) throws BusinessException;
	
	public void voteComentario(VotoComentario voto) throws BusinessException;

}
