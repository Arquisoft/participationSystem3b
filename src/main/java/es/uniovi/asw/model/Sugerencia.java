package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.uniovi.asw.model.types.SugerenciaStatus;

@SuppressWarnings("serial")
@Entity
@Table(name="TSugerencia")
public class Sugerencia implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
	@ManyToOne
	private Citizen citizen;
	private String titulo;
	private String contenido;
	@OneToMany(mappedBy="sugerencia") 
	private Set<Comentario> comentarios = new HashSet<>();
	@OneToMany(mappedBy="sugerencia") 
	private Set<VotosSugerencias> votos = new HashSet<>();
	@Enumerated(EnumType.STRING) private SugerenciaStatus estado;
	@ManyToOne
	private Categoria categoria;
}
