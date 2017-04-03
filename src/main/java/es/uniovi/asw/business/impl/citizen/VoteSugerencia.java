package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.persistence.util.Jpa;
import es.uniovi.asw.producers.KafkaProducer;
import es.uniovi.asw.producers.Topics;
import es.uniovi.asw.webService.Message;

public class VoteSugerencia implements Command{

	private VotoSugerencia voto;
	
	public VoteSugerencia(VotoSugerencia voto) {
		this.voto = voto;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(voto);
		Jpa.getManager().merge(voto.getSugerencia());
		new KafkaProducer().send(Topics.VOTE_SUGGESTION, Message.setMessage(voto)); //Enviar kafka
		if(voto.getSugerencia().getVotosTotal()>=voto.getSugerencia().getCategoria().getMinimoVotos()){
			voto.getSugerencia().setEstado(SugerenciaStatus.Aceptada); //Pasar a aceptada
			new KafkaProducer().send(Topics.ACCEPT_SUGGESTION, Message.setMessage(voto.getSugerencia())); //Enviar kafka
		}
			
		return voto;
	}
}
