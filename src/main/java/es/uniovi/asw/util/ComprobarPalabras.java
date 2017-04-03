package es.uniovi.asw.util;

import java.util.List;

import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.types.SugerenciaStatus;

public class ComprobarPalabras {

	public static void comprobarPalabras(Sugerencia sugerencia){
		String titulo = sugerencia.getTitulo();
		String contenido = sugerencia.getContenido();
		
		List<String> palabras = sugerencia.getCategoria().getPalabrasNoPermitidas();
		boolean nopermitida = false;
		String titulof = titulo;
		String contenidof = contenido;
		for(int i=0; i<palabras.size();i++){ //Para las palabras no permitidas se comprueba si están en el título o el contenido
			if(titulof.matches(".*\\b"+palabras.get(i)+"\\b.*") || contenidof.matches(".*\\b"+palabras.get(i)+"\\b.*")){
				nopermitida = true; //Si está se pasa a true
				titulof = titulof.replaceAll("\\b"+palabras.get(i)+"\\b", "****"); //Se sustituyen las apariciones de la palabra por asteriscos
				contenidof = contenidof.replaceAll("\\b"+palabras.get(i)+"\\b", "****"); //Lo mismo
			}
		}
		
		if(nopermitida){ //Pasar a anulada si tiene palabras no permitidas y cambiar el titulo y el contenido por los que tienen los asteriscos
			sugerencia.setEstado(SugerenciaStatus.Anulada);
			sugerencia.setTitulo(titulof);
			sugerencia.setContenido(contenidof);
		}else{
			sugerencia.setEstado(SugerenciaStatus.EnVotacion);
		}
		

	}
}
