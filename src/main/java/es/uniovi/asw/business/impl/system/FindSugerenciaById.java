package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.SugerenciaFinder;

public class FindSugerenciaById implements Command {
	
	private Long id;

	public FindSugerenciaById(Long id) {
		this.id = id;
	}
	
	@Override
	public Object execute() throws BusinessException {
		return SugerenciaFinder.findById(id);
	}

}
