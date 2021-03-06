package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class VoteSugerencia implements Command{

	private VotoSugerencia voto;
	
	public VoteSugerencia(VotoSugerencia voto) {
		this.voto = voto;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(voto);
		Jpa.getManager().merge(voto.getSugerencia());
					
		return voto;
	}
}
