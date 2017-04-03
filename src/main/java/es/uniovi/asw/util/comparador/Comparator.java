package es.uniovi.asw.util.comparador;

public class Comparator {
	
	public static ComparadorComentarios getComentarios(){
		return new ComparadorComentarios();
	}
	
	public static ComparadorSugerencias getSugerencias() {
		return new ComparadorSugerencias();
	}

}
