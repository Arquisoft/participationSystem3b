package es.uniovi.asw.webService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.util.comparador.Comparator;

public class SugerenciaVista {
	
	private Sugerencia sugerencia;
	private List<ComentarioVista> comentarios = new ArrayList<>();
	private int posVotes;
	private int negVotes;
	
	public SugerenciaVista(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
	}
	
	public int getPosVotes() {
		posVotes = sugerencia.getVotos().stream().filter(v->v.isAFavor()).toArray().length;
		return posVotes;
	}

	public int getNegVotes() {
		negVotes = sugerencia.getVotos().stream().filter(v->!v.isAFavor()).toArray().length;
		return negVotes;
	}

	public Sugerencia getSugerencia() {
		return sugerencia;
	}

	public void setSugerencia(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
	}

	public void setPosVotes(int posVotes) {
		this.posVotes = posVotes;
	}

	public void setNegVotes(int negVotes) {
		this.negVotes = negVotes;
	}

	public List<ComentarioVista> getComentarios() {
		for (Comentario c: sugerencia.getComentarios())
			comentarios.add(new ComentarioVista(c));
		return comentarios.stream().
				sorted(Comparator.getComentarios()).collect(Collectors.toList());
	}

	public void setComentarios(List<ComentarioVista> comentarios) {
		this.comentarios = comentarios;
	}

}
