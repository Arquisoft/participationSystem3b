package es.uniovi.asw.util.comparador;

import java.util.Comparator;

import es.uniovi.asw.model.Comentario;

public class ComparadorComentarios implements Comparator<Comentario> {

	@Override
	public int compare(Comentario o1, Comentario o2) {
		return new Integer(o2.getVotosTotal()).compareTo(new Integer(o1.getVotosTotal()));
	}

}
