package es.uniovi.asw.business.impl.citizen;

import java.util.Set;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.persistence.util.Jpa;

public class VoteSugerencia implements Command{

	private VotoSugerencia voto;
	
	public VoteSugerencia(VotoSugerencia voto) {
		this.voto = voto;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(voto);
		Set<VotoSugerencia> votos = voto.getSugerencia().getVotos();
		int n = 0;
		for(VotoSugerencia v:votos){
			if (v.isAFavor())
				n++;
			else
				n--;
		}
		
		if(n>=voto.getSugerencia().getCategoria().getMinimoVotos()){
			voto.getSugerencia().setEstado(SugerenciaStatus.Aceptada);
			//Enviar Kafka
		}
			
		return voto;
	}
}
