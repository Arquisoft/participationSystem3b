package es.uniovi.asw.business.impl.admin;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.persistence.util.Jpa;

public class AnularSugerencia implements Command{
	
	private Long idSugerencia;
	
	public AnularSugerencia(Long id) {
		this.idSugerencia = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Sugerencia sugerencia = Jpa.getManager().find(Sugerencia.class, idSugerencia);
		sugerencia.setEstado(SugerenciaStatus.Anulada);
		return sugerencia;
	}
	
}
