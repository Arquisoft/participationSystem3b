package es.uniovi.asw.business.impl.citizen;

import java.util.List;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.persistence.util.Jpa;
import es.uniovi.asw.producers.KafkaProducer;
import es.uniovi.asw.producers.Topics;
import es.uniovi.asw.webService.Message;

public class AddSugerencia implements Command{
	
	private Sugerencia sugerencia;
	
	public AddSugerencia(Sugerencia sugerencia) {
		this.sugerencia = sugerencia;
	}

	@Override
	public Object execute() throws BusinessException {
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
		}
		
		Jpa.getManager().persist(sugerencia); //Meter en bd
		
		new KafkaProducer().send(Topics.CREATE_SUGGESTION, Message.setMessage(sugerencia)); //Enviar kafka
		 
		
		return sugerencia;
	}

}
