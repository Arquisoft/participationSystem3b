package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TVotosSugerencias")
public class VotosSugerencias {

	@Id @ManyToOne private Sugerencia sugerencia;
	@Id @ManyToOne private Citizen citizen;
	private boolean isAFavor;
}
