package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Administrador;
import es.uniovi.asw.persistence.util.Jpa;

public class AdministradorFinder {
	
	public static Object findByUserAndPass(String user, String pass) {
		return Jpa.getManager().createNamedQuery("Admin.findByUserAndPass", Administrador.class).
				setParameter(1, user).setParameter(2, pass).getSingleResult();
	}

}
