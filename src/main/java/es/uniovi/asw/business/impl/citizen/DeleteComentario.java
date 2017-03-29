package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class DeleteComentario implements Command {
	
	private Long idComentario;
	
	public DeleteComentario(Long id) {
		this.idComentario = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Comentario comentario = Jpa.getManager().find(Comentario.class, idComentario);
		Jpa.getManager().remove(comentario);
		return comentario;
	}

}
