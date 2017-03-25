package es.uniovi.asw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="TCategoria")
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
	@OneToMany(mappedBy="categoria") 
	private Set<Sugerencia> sugerencias = new HashSet<>();
	private String nombre;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	private List<String> palabrasNoPermitidas;
}
