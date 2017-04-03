package es.uniovi.asw.webService;

import es.uniovi.asw.model.Comentario;

public class ComentarioVista {
	
	private Comentario comentario;
	private int posVotes;
	private int negVotes;
	
	public ComentarioVista(Comentario comentario) {
		this.comentario = comentario;
	}

	public int getPosVotes() {
		posVotes = comentario.getVotos().stream().filter(v->v.isAFavor()).toArray().length;
		return posVotes;
	}

	public void setPosVotes(int posVotes) {
		this.posVotes = posVotes;
	}

	public int getNegVotes() {
		negVotes = comentario.getVotos().stream().filter(v->!v.isAFavor()).toArray().length;
		return negVotes;
	}

	public void setNegVotes(int negVotes) {
		this.negVotes = negVotes;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	

}
