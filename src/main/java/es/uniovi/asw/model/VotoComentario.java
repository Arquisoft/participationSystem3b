package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import es.uniovi.asw.model.types.VotosComentariosKey;

@SuppressWarnings("serial")
@Entity
@IdClass(VotosComentariosKey.class)
@Table(name="TVOTOCOMENTARIO")
public class VotoComentario implements Serializable{
	@Id @ManyToOne @Expose private Comentario comentario;
	@Id @ManyToOne @Expose private Citizen citizen;
	@Expose private boolean isAFavor;

	public VotoComentario(Comentario comentario, Citizen citizen, boolean isAFavor) {
		super();
		this.isAFavor = isAFavor;
		if (!comentario.getVotos().stream().map(v->v.getCitizen().getId()).
				collect(Collectors.toList()).contains(citizen.getId()))
		Association.VotarComentario.link(comentario,this,citizen);
	}
	public VotoComentario() {}
	
	public Comentario getComentario() {
		return comentario;
	}
    void _setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	public Citizen getCitizen() {
		return citizen;
	}
	void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	public boolean isAFavor() {
		return isAFavor;
	}
	public void setAFavor(boolean isAFavor) {
		this.isAFavor = isAFavor;
	}
	
	
	
}
