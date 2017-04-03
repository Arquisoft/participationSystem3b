package es.uniovi.asw.business.impl.citizen;

import java.util.List;

import es.uniovi.asw.business.impl.Command;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.persistence.util.Jpa;
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
		for(int i=0; i<palabras.size();i++){ //Para las palabras no permitidas se comprueba si están en el título o el contenido
			if(titulo.matches(".*\\b"+palabras.get(i)+"\\b.*") || contenido.matches(".*\\b"+palabras.get(i)+"\\b.*")){
				nopermitida = true; //Si está se pasa a true
				break; //Y se sale del bucle
			}
		}
		
		if(nopermitida) //Pasar a anulada si tiene palabras no permitidas
			sugerencia.setEstado(SugerenciaStatus.Anulada);
		
		Jpa.getManager().persist(sugerencia); //Meter en bd
		
		Message.setMessage(sugerencia); //Enviar kafka
		
		return sugerencia;
	}

}
