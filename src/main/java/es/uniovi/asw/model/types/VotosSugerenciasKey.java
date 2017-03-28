package es.uniovi.asw.model.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VotosSugerenciasKey implements Serializable{

	Long citizen;
	Long sugerencia;
	
	public VotosSugerenciasKey(Long citizen, Long sugerencia) {
		this.citizen = citizen;
		this.sugerencia = sugerencia;
	}

	public VotosSugerenciasKey() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((sugerencia == null) ? 0 : sugerencia.hashCode());
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
		VotosSugerenciasKey other = (VotosSugerenciasKey) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (sugerencia == null) {
			if (other.sugerencia != null)
				return false;
		} else if (!sugerencia.equals(other.sugerencia))
			return false;
		return true;
	}
	
	
}
