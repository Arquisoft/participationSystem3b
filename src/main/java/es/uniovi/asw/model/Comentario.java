package es.uniovi.asw.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TComentario")
public class Comentario {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
	@ManyToOne
	private Citizen citizen;
	@ManyToOne
	private Sugerencia sugerencia;
	private String contenido;
	@OneToMany(mappedBy="comentario") 
	private Set<VotosComentarios> votos = new HashSet<>();
}
