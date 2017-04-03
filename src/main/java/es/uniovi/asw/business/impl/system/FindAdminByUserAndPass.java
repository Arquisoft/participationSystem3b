package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.AdministradorFinder;

public class FindAdminByUserAndPass implements Command {
	
	private String usuario;
	private String password;

	public FindAdminByUserAndPass(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	@Override
	public Object execute() throws BusinessException {
		return AdministradorFinder.findByUserAndPass(usuario, password);
	}

}
