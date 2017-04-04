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
		Categoria defecto = Jpa.getManager().find(Categoria.class, 1L);
		Categoria categoria = Jpa.getManager().find(Categoria.class, idCategoria);

		
		categoria.borrar(defecto); //Hace unlink entre sugerencia y categoria
		Jpa.getManager().remove(categoria);
		

		return categoria;
	}

}
