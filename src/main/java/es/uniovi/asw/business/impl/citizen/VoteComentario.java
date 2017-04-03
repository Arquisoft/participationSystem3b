package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;
import es.uniovi.asw.producers.KafkaProducer;
import es.uniovi.asw.producers.Topics;
import es.uniovi.asw.webService.Message;

public class VoteComentario implements Command {
	
	private VotoComentario voto;
	
	public VoteComentario(VotoComentario voto) {
		this.voto = voto;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(voto);

		new KafkaProducer().send(Topics.VOTE_COMMENT, Message.setMessage(voto)); //Enviar kafka
		
		return voto;
	}

}
