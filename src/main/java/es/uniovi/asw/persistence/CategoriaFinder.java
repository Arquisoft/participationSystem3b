package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.persistence.util.Jpa;

public class CategoriaFinder {
	
	public static List<Categoria> findAll() {
		return Jpa.getManager().createNamedQuery("Categoria.findAll", Categoria.class).getResultList();
	}
	
}
