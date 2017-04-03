package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import es.uniovi.asw.model.types.VotosSugerenciasKey;

@SuppressWarnings("serial")
@Entity
@IdClass(VotosSugerenciasKey.class)
@Table(name="TVOTOSUGERENCIA")
public class VotoSugerencia implements Serializable{

	@Id @ManyToOne @Expose private Sugerencia sugerencia;
	@Id @ManyToOne @Expose private Citizen citizen;
	@Expose private boolean isAFavor;
	
	public VotoSugerencia(Sugerencia sugerencia, Citizen citizen, boolean isAFavor) {
		super();
		this.isAFavor = isAFavor;
		if (!sugerencia.getVotos().stream().map(v->v.getCitizen().getId()).
				collect(Collectors.toList()).contains(citizen.getId()))
		Association.VotarSugerencia.link(sugerencia,this,citizen);
	}

	public VotoSugerencia() {}

	public Sugerencia getSugerencia() {
		return sugerencia;
	}

	void _setSugerencia(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
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
