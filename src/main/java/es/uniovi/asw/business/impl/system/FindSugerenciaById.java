package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class FindSugerenciaById implements Command {
	
	private Long id;

	public FindSugerenciaById(Long id) {
		this.id = id;
	}
	
	@Override
	public Object execute() throws BusinessException {
		return Jpa.getManager().find(Sugerencia.class, id);
	}

}
