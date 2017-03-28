package es.uniovi.asw.model.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VotosComentariosKey implements Serializable{

	Long citizen;
	Long comentario;
	
	public VotosComentariosKey(Long citizen, Long comentario) {
		this.citizen = citizen;
		this.comentario = comentario;
	}

	public VotosComentariosKey() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
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
		VotosComentariosKey other = (VotosComentariosKey) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		return true;
	}
	
	
	
}
