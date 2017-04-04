package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;
import es.uniovi.asw.util.ComprobarPalabras;

public class UpdateSugerencia implements Command {
	
	private Sugerencia sugerencia;
	
	public UpdateSugerencia(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
	}

	@Override
	public Object execute() throws BusinessException {
		ComprobarPalabras.comprobarPalabras(sugerencia);
		Jpa.getManager().merge(sugerencia);
		Jpa.getManager().merge(sugerencia.getCategoria());
		return sugerencia;
	}

}
