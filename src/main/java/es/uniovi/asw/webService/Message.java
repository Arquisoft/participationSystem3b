package es.uniovi.asw.webService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoComentario;
import es.uniovi.asw.model.VotoSugerencia;

public class Message {
	
	public static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static String setMessage(Sugerencia sugerencia) {
        return gson.toJson(sugerencia);
    }
    
    public static String setMessage(Comentario comentario) {
    	return gson.toJson(comentario);
    }
    
    public static String setMessage(VotoComentario votoComentario) {
    	return gson.toJson(votoComentario);
    }
    
    public static String setMessage(VotoSugerencia votoSusgerencia) {
    	return gson.toJson(votoSusgerencia);
    }

}
