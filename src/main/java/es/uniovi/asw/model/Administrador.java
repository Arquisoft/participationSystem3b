package es.uniovi.asw.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Administrador {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
	private String usuario;
	private String password;
}
