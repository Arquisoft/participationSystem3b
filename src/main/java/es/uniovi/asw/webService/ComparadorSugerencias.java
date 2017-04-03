package es.uniovi.asw.webService;

import java.util.Comparator;

import es.uniovi.asw.model.Sugerencia;

public class ComparadorSugerencias implements Comparator<Sugerencia> {

	@Override
	public int compare(Sugerencia o1, Sugerencia o2) {
		return new Integer(o2.getVotosTotal()).compareTo(new Integer(o1.getVotosTotal()));
	}

}
