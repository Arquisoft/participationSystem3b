package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class AddComentario implements Command {
	
	private Comentario comentario;
	
	public AddComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(comentario);
		return comentario;
	}

}
