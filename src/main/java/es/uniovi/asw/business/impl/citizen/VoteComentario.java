package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class VoteComentario implements Command {
	
	private VotoComentario voto;
	
	public VoteComentario(VotoComentario voto) {
		this.voto = voto;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(voto);
		
		return voto;
	}

}
