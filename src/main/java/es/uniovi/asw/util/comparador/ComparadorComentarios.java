package es.uniovi.asw.util.comparador;

import java.util.Comparator;

import es.uniovi.asw.webService.ComentarioVista;

public class ComparadorComentarios implements Comparator<ComentarioVista> {

	@Override
	public int compare(ComentarioVista o1, ComentarioVista o2) {
		return new Integer(o2.getComentario().getVotosTotal()).compareTo(new Integer(o1.getComentario().getVotosTotal()));
	}

}
