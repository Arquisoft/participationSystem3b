package es.uniovi.asw.business.impl.admin;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.persistence.util.Jpa;

public class DeleteCategoria implements Command {
	
	private Long idCategoria;
	
	public DeleteCategoria(Long id) {
		this.idCategoria = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Categoria categoria = Jpa.getManager().find(Categoria.class, idCategoria);
		Jpa.getManager().remove(categoria);
		return categoria;
	}

}
