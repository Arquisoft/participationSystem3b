package es.uniovi.asw.webService;

import es.uniovi.asw.model.Sugerencia;

public class SugerenciaVista {
	
	private Sugerencia sugerencia;
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

}
