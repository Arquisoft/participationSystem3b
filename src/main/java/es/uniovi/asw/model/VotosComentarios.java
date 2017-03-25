package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TVotosComentarios")
public class VotosComentarios {
	@Id @ManyToOne private Comentario comentario;
	@Id @ManyToOne private Citizen citizen;
	private boolean isAFavor;
}
