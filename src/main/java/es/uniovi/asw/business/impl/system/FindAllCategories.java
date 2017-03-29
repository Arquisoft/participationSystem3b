package es.uniovi.asw.business.impl.system;

import java.util.List;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.CategoriaFinder;

public class FindAllCategories implements Command {
	
	@Override
	public List<Categoria> execute() throws BusinessException {
		return CategoriaFinder.findAll();
	}

}
