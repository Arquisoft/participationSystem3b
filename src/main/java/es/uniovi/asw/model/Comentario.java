package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import es.uniovi.asw.persistence.util.Jpa;

@SuppressWarnings("serial")
@Entity
@Table(name="TCOMENTARIO")
public class Comentario implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose private long id;
	@ManyToOne @Expose
	private Citizen citizen;
	@ManyToOne @Expose
	private Sugerencia sugerencia;
	@Expose private String contenido;
	@OneToMany(mappedBy="comentario") 
	private Set<VotoComentario> votos = new HashSet<>();
	
	public Comentario(Citizen citizen, Sugerencia sugerencia, String contenido) {
		super();
		this.contenido = contenido;
		Association.Comentar.link(citizen,this,sugerencia);
	}

	public Comentario() {}

	public Citizen getCitizen() {
		return citizen;
	}

	void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public Sugerencia getSugerencia() {
		return sugerencia;
	}

	void _setSugerencia(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Set<VotoComentario> getVotos() {
		return new HashSet<>(votos);
	}

	Set<VotoComentario> _getVotos() {
		return votos;
	}

	public long getId() {
		return id;
	}
	
	public int getPosVotes() {
		return votos.stream().filter(v->v.isAFavor()).toArray().length;
	}

	public int getNegVotes() {
		return votos.stream().filter(v->!v.isAFavor()).toArray().length;
	}
	
	public int getVotosTotal() {
		return getPosVotes() - getNegVotes();
	}

	public void borrar() {
		for(VotoComentario vc:votos){
			//Association.VotarComentario.unlink(this, vc, citizen);
			Jpa.getManager().remove(vc);
		}
		Association.Comentar.unlink(citizen, this, sugerencia);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", contenido=" + contenido + "]";
	}
	
	
}
