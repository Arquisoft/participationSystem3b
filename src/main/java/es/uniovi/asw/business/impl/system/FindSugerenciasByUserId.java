package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.SugerenciaFinder;

public class FindSugerenciasByUserId implements Command {

	private Long idUser;

	public FindSugerenciasByUserId(Long id) {
		this.idUser = id;
	}

	@Override
	public Object execute() throws BusinessException {
		return SugerenciaFinder.findByUserId(idUser);
	}

}
