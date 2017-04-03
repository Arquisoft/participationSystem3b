package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
@Entity
@Table(name="TCITIZENS")
public class Citizen implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose private long id;
	@Expose private String nombre;
	@Expose private String apellidos;
	@Expose private String email;
	@Expose @Temporal(TemporalType.DATE) private Date fechaNacimiento;
	@Expose private String direccion;
	@Expose private String nacionalidad;
	@Expose @NotNull private String DNI;
	@Expose private String usuario;
	@Expose private String password;
	//---------------------------------------------------------------------------
	@OneToMany(mappedBy="citizen") 
	private Set<Sugerencia> sugerencias = new HashSet<>();
	@OneToMany(mappedBy="citizen") 
	private Set<Comentario> comentarios = new HashSet<>();
	@OneToMany(mappedBy="citizen") 
	private Set<VotoComentario> votosComentarios = new HashSet<>();
	@OneToMany(mappedBy="citizen") 
	private Set<VotoSugerencia> votosSugerencias = new HashSet<>();

	
	
	public Citizen(){}


	public Citizen(String nombre, String apellidos, String email, Date fechaNacimiento, String direccion,
			String nacionalidad, String dNI, String usuario, String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
		DNI = dNI;
		this.usuario = usuario;
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
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

	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

    
    Set<Sugerencia> _getSugerencias() {
		return sugerencias;
	}

	public Set<Sugerencia> getSugerencias() {
		return new HashSet<>(sugerencias);
	}
	Set<Comentario> _getComentarios() {
		return comentarios;
	}
	public Set<Comentario> getComentarios() {
		return new HashSet<>(comentarios);
	}

	Set<VotoComentario> _getVotosComentarios() {
		return votosComentarios;
	}
	public Set<VotoComentario> getVotosComentarios() {
		return new HashSet<>(votosComentarios);
	}


	Set<VotoSugerencia> _getVotosSugerencias() {
		return votosSugerencias;
	}

	public Set<VotoSugerencia> getVotosSugerencias() {
		return new HashSet<>(votosSugerencias);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
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
		Citizen other = (Citizen) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Citizen [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + ", nacionalidad=" + nacionalidad
				+ ", DNI=" + DNI + ", password=" + password + "]";
	}
}
