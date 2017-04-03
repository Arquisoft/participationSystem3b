package es.uniovi.asw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
@Entity
@Table(name="TADMINISTRADOR")
public class Administrador implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose private long id;
	@Expose private String usuario;
	@Expose private String password;
	
	public Administrador(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
	}

	
	public Administrador() {}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", usuario=" + usuario + "]";
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
		Administrador other = (Administrador) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
