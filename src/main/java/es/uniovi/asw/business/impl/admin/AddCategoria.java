package es.uniovi.asw.business.impl.admin;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class AddCategoria implements Command {
	
	private Categoria categoria;
	
	public AddCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().persist(categoria);
		return categoria;
	}

}
