package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.persistence.util.Jpa;

public class SugerenciaFinder {
	
	public static List<Sugerencia> findAll() {
		return Jpa.getManager().createNamedQuery("Sugerencia.findAll", Sugerencia.class).getResultList();
	}

	public static Object findByCategory(Long idCategory) {
		return Jpa.getManager().createNamedQuery("Sugerencia.findByCategory", Sugerencia.class).
			setParameter(1, idCategory).getResultList();
	}

	public static List<Sugerencia> findByUserId(Long idUser) {
		return Jpa.getManager().createNamedQuery("Sugerencia.findByUser", Sugerencia.class).
				setParameter(1, idUser).getResultList();
	}

}
