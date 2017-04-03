package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.impl.citizen.AddComentario;
import es.uniovi.asw.business.impl.citizen.AddSugerencia;
import es.uniovi.asw.business.impl.citizen.DeleteComentario;
import es.uniovi.asw.business.impl.citizen.DeleteSugerencia;
import es.uniovi.asw.business.impl.citizen.FindComentarioById;
import es.uniovi.asw.business.impl.citizen.UpdateSugerencia;
import es.uniovi.asw.business.impl.citizen.VoteComentario;
import es.uniovi.asw.business.impl.citizen.VoteSugerencia;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public class CitizenServiceImpl extends SuperService implements CitizenService {
	
	private CommandExecutor cmd = new CommandExecutor();
	
	@Override
	public void deleteSugerencia(Long id) throws BusinessException {
		cmd.execute(new DeleteSugerencia(id));
	}

	@Override
	public void deleteComentario(Long id) throws BusinessException {
		cmd.execute(new DeleteComentario(id));
	}

	@Override
	public void addSugerencia(Sugerencia sugerencia) throws BusinessException {
		cmd.execute(new AddSugerencia(sugerencia));
	}

	@Override
	public void addComentario(Comentario comentario) throws BusinessException {
		cmd.execute(new AddComentario(comentario));
		
	}

	@Override
	public void updateSugerencia(Sugerencia sugerencia) throws BusinessException {
		cmd.execute(new UpdateSugerencia(sugerencia));
	}

	@Override
	public void voteSugerencia(VotoSugerencia voto) throws BusinessException {
		cmd.execute(new VoteSugerencia(voto));
	}

	@Override
	public void voteComentario(VotoComentario voto) throws BusinessException {
		cmd.execute(new VoteComentario(voto));
	}

	@Override
	public Comentario findComentarioById(Long id) throws BusinessException {
		return (Comentario) cmd.execute(new FindComentarioById(id));
	}

}
