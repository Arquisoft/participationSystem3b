package es.uniovi.asw.business;

import es.uniovi.asw.model.Categoria;

public interface AdminService {
	
	public void addCategoria(Categoria categoria);
	
	public void updateCategoria(Categoria categoria);
	
	public void deleteCategoria(Long id);
	
	public void deleteSugerencia(Long id);
}
