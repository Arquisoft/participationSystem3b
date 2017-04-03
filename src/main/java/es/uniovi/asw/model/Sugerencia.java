package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.util.comparador.ComparadorComentarios;
import es.uniovi.asw.util.comparador.Comparator;
import scala.collection.parallel.ParIterableLike.Collect;

@SuppressWarnings("serial")
@Entity
@Table(name="TSUGERENCIA")
public class Sugerencia implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose private long id;
	@ManyToOne @Expose
	private Citizen citizen;
	@Expose private String titulo;
	@Expose private String contenido;
	@OneToMany(mappedBy="sugerencia") 
	private Set<Comentario> comentarios = new HashSet<>();
	@OneToMany(mappedBy="sugerencia") 
	private Set<VotoSugerencia> votos = new HashSet<>();
	@Expose @Enumerated(EnumType.STRING) private SugerenciaStatus estado;
	@ManyToOne @Expose
	private Categoria categoria;
	
	
	public Sugerencia(Citizen citizen, String titulo, String contenido, Categoria categoria) {
		super();
		
		this.titulo = titulo;
		this.contenido = contenido;
		this.estado = SugerenciaStatus.EnVotacion;
		Association.Sugerir.link(citizen,this,categoria);

	}

	public Sugerencia() {}

	public Citizen getCitizen() {
		return citizen;
	}

	void _setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public SugerenciaStatus getEstado() {
		return estado;
	}

	public void setEstado(SugerenciaStatus estado) {
		this.estado = estado;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	void _setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public Set<Comentario> getComentarios() {
		return new HashSet<>(comentarios);
	}

	Set<Comentario> _getComentarios() {
		return comentarios;
	}
	Set<VotoSugerencia> _getVotos() {
		return votos;
	}
	public Set<VotoSugerencia> getVotos() {
		return new HashSet<>(votos);
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

	public void borrar(){
		Association.Sugerir.unlink(citizen,this, categoria);
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
		Sugerencia other = (Sugerencia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sugerencia [id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + ", estado=" + estado
				+ ", categoria=" + categoria + "]";
	}
	
	
	
}
