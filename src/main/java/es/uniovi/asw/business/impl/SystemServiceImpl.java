package es.uniovi.asw.business.impl;

import java.util.List;

import es.uniovi.asw.business.SystemService;
import es.uniovi.asw.business.impl.system.FindAdminByUserAndPass;
import es.uniovi.asw.business.impl.system.FindAllCategories;
import es.uniovi.asw.business.impl.system.FindAllSugerencias;
import es.uniovi.asw.business.impl.system.FindCategoriaById;
import es.uniovi.asw.business.impl.system.FindCitizenByUserAndPass;
import es.uniovi.asw.business.impl.system.FindSugerenciaById;
import es.uniovi.asw.business.impl.system.FindSugerenciasByCategory;
import es.uniovi.asw.business.impl.system.FindSugerenciasByUserId;
import es.uniovi.asw.model.Administrador;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;

public class SystemServiceImpl extends SuperService implements SystemService {
	
	private CommandExecutor cmd = new CommandExecutor();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findAllCategories() throws BusinessException {
		return (List<Categoria>) cmd.execute(new FindAllCategories());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sugerencia> findAllSugerencias() throws BusinessException {
		return (List<Sugerencia>) cmd.execute(new FindAllSugerencias());
	}

	@Override
	public Sugerencia findSugerenciaById(Long id) throws BusinessException {
		return (Sugerencia) cmd.execute(new FindSugerenciaById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sugerencia> findSugerenciasByCategory(Long idCategory) throws BusinessException {
		return (List<Sugerencia>) cmd.execute(new FindSugerenciasByCategory(idCategory));
	}

	@Override
	public Citizen findCitizenByUserAndPass(String usuario, String password) throws BusinessException {
		return (Citizen) cmd.execute(new FindCitizenByUserAndPass(usuario, password));
	}

	@Override
	public Categoria findCategoriaById(Long id) throws BusinessException {
		return (Categoria) cmd.execute(new FindCategoriaById(id));
	}

	@Override
	public Administrador findAdminByUserAndPass(String usuario, String password) throws BusinessException {
		return (Administrador) cmd.execute(new FindAdminByUserAndPass(usuario, password));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sugerencia> findSugerenciasByUserId(Long id) throws BusinessException {
		return (List<Sugerencia>) cmd.execute(new FindSugerenciasByUserId(id));
	}
	
	

}
