package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.AdminService;
import es.uniovi.asw.business.impl.admin.AddCategoria;
import es.uniovi.asw.business.impl.admin.AnularSugerencia;
import es.uniovi.asw.business.impl.admin.DeleteCategoria;
import es.uniovi.asw.business.impl.admin.DeleteSugerencia;
import es.uniovi.asw.business.impl.admin.UpdateCategoria;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.exception.BusinessException;

public class AdminServiceImpl extends SuperService implements AdminService {
	
	private CommandExecutor cmd = new CommandExecutor();
	
	@Override
	public void addCategoria(Categoria categoria) throws BusinessException {
		cmd.execute(new AddCategoria(categoria));
	}

	@Override
	public void updateCategoria(Categoria categoria) throws BusinessException {
		cmd.execute(new UpdateCategoria(categoria));
	}

	@Override
	public void deleteCategoria(Long id) throws BusinessException {
		cmd.execute(new DeleteCategoria(id));
	}

	@Override
	public void deleteSugerencia(Long id) throws BusinessException {
		cmd.execute(new DeleteSugerencia(id));
	}

	@Override
	public void anularSugerencia(Long id) throws BusinessException {
		cmd.execute(new AnularSugerencia(id));
	}
}
