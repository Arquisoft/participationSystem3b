package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.persistence.util.Jpa;

public class SugerenciaFinder {
	
	public static List<Sugerencia> findAll() {
		return Jpa.getManager().createNamedQuery("Sugerencia.findAll", Sugerencia.class).getResultList();
	}
	
	public static Sugerencia findById(Long id) {
		return Jpa.getManager().createNamedQuery("Sugerencia.findById", Sugerencia.class).getSingleResult();
	}

}
