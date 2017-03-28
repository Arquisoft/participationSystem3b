package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Sugerencia;

public interface SystemService {
	
	public List<Categoria> findAllCategories();
	
	public List<Sugerencia> findAllSugerencias();
	
	public Sugerencia findSugerenciaById();

}
