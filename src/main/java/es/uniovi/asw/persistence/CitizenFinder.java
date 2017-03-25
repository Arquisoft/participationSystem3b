package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.util.Jpa;

public class CitizenFinder {

	public static Citizen findByDNI(String dni) {
		return (Citizen) Jpa.getManager().createNamedQuery("Citizen.findByDNI", Citizen.class).setParameter(1, dni)
				.getResultList().stream().findFirst().orElse(null);
	}

	public static List<Citizen> findAll() {
		return Jpa.getManager().createNamedQuery("Citizen.findAll", Citizen.class).getResultList();
	}
}
