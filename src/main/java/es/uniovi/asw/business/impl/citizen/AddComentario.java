package es.uniovi.asw.business.impl.citizen;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;
import es.uniovi.asw.producers.KafkaProducer;
import es.uniovi.asw.producers.Topics;
import es.uniovi.asw.webService.Message;

public class AddComentario implements Command {
	
	private Comentario comentario;
	
	public AddComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(comentario);
		Jpa.getManager().merge(comentario.getSugerencia());

		new KafkaProducer().send(Topics.COMMENT_SUGGESTION, Message.setMessage(comentario)); //Enviar kafka
		
		return comentario;
	}

}
