package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public interface SystemService {
	
	public List<Categoria> findAllCategories() throws BusinessException;
	
	public List<Sugerencia> findAllSugerencias() throws BusinessException;
	
	public Sugerencia findSugerenciaById(Long id) throws BusinessException;

}
