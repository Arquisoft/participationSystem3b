package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class FindComentarioById implements Command {
	private Long id;

	public FindComentarioById(Long id) {
		this.id = id;	
	}

	@Override
	public Object execute() throws BusinessException {
		return Jpa.getManager().find(Comentario.class, id);
	}

}
