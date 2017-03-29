package es.uniovi.asw.business.impl.system;

import java.util.List;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.SugerenciaFinder;

public class FindAllSugerencias implements Command {
	
	@Override
	public List<Sugerencia> execute() throws BusinessException {
		return SugerenciaFinder.findAll();
	}

}
