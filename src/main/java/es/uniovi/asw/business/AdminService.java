package es.uniovi.asw.business;

import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.exception.BusinessException;

public interface AdminService {
	
	public void addCategoria(Categoria categoria) throws BusinessException;
	
	public void updateCategoria(Categoria categoria) throws BusinessException;
	
	public void deleteCategoria(Long id) throws BusinessException;
	
	public void deleteSugerencia(Long id) throws BusinessException;

	public void anularSugerencia(Long id) throws BusinessException;
}
