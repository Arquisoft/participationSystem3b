package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

import es.uniovi.asw.model.exception.BusinessException;

@SuppressWarnings("serial")
@Entity
@Table(name="TCATEGORIA")
public class Categoria implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose private long id;
	@OneToMany(mappedBy="categoria") 
	private Set<Sugerencia> sugerencias = new HashSet<>();
	@Expose private String nombre;
	@Temporal(TemporalType.DATE)
	@Expose private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	@Expose private Date fechaFin;
	@Expose private int minimoVotos;
	@ElementCollection
    @CollectionTable(name = "TPALABRAS")
    @Column(name="PALABRA")
	@Expose private List<String> palabrasNoPermitidas;
	
	public Categoria(String nombre, Date fechaInicio, Date fechaFin, int minimoVotos) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.minimoVotos = minimoVotos;
	}

	public Categoria(String nombre, Date fechaInicio, Date fechaFin, int minimoVotos,
			List<String> palabrasNoPermitidas) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.minimoVotos = minimoVotos;
		this.palabrasNoPermitidas = palabrasNoPermitidas;
	}

	
	public Categoria() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getMinimoVotos() {
		return minimoVotos;
	}

	public void setMinimoVotos(int minimoVotos) {
		this.minimoVotos = minimoVotos;
	}

	public List<String> getPalabrasNoPermitidas() {
		return palabrasNoPermitidas;
	}

	public void setPalabrasNoPermitidas(List<String> palabrasNoPermitidas) {
		this.palabrasNoPermitidas = palabrasNoPermitidas;
	}

	public long getId() {
		return id;
	}

    Set<Sugerencia> _getSugerencias() {
		return sugerencias;
	}
	
	public Set<Sugerencia> getSugerencias() {
		return new HashSet<>(sugerencias);
	}
	
	public void borrar(Categoria c) throws BusinessException{
	
		palabrasNoPermitidas = new ArrayList<String>();

		for(Sugerencia s:sugerencias){
			Association.Sugerir.unlink(s, this);
			Association.Sugerir.link(s, c);

		}
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
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", minimoVotos=" + minimoVotos + ", palabrasNoPermitidas=" + palabrasNoPermitidas + "]";
	}
	
	
}
