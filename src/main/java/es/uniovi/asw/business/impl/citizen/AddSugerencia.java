package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class AddSugerencia implements Command{
	
	private Sugerencia sugerencia;
	
	public AddSugerencia(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(sugerencia);
		return sugerencia;
	}

}
