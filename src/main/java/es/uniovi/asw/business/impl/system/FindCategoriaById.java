package es.uniovi.asw.business.impl.system;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class FindCategoriaById implements Command {
	
	private Long id;

	public FindCategoriaById(Long id) {
		this.id = id;
	}
	
	@Override
	public Object execute() throws BusinessException {
		return Jpa.getManager().find(Categoria.class, id);
	}

}
