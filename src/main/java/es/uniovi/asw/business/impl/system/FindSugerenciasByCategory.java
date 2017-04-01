package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.SugerenciaFinder;

public class FindSugerenciasByCategory implements Command {
	
	private Long idCategory;

	public FindSugerenciasByCategory(Long id) {
		this.idCategory = id;
	}
	
	@Override
	public Object execute() throws BusinessException {
		return SugerenciaFinder.findByCategory(idCategory);
	}

}
