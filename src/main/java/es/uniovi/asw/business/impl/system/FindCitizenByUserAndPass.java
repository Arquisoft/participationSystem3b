package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.CitizenFinder;

public class FindCitizenByUserAndPass implements Command {
	
	public String usuario;
	public String password;
	
	public FindCitizenByUserAndPass(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	@Override
	public Object execute() throws BusinessException {
		return CitizenFinder.findByUserAndPass(usuario, password);
	}
	
	

}
