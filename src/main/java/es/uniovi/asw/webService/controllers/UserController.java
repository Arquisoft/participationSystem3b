package es.uniovi.asw.webService.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.business.Services;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.model.types.SugerenciaStatus;
import es.uniovi.asw.persistence.util.Jpa;
import es.uniovi.asw.producers.KafkaProducer;
import es.uniovi.asw.producers.Topics;
import es.uniovi.asw.webService.Message;
import es.uniovi.asw.webService.SugerenciaVista;

@Controller
@Scope("session")
public class UserController {
	
	 @Autowired
	 private KafkaProducer kafkaProducer;
	 
	 @RequestMapping(value = "/ver", method = RequestMethod.POST)
	    public String AbrirSolicitud(@RequestParam("sugerencia") Long id, HttpSession session,Model model) throws BusinessException {
	    		Sugerencia sugerencia = Services.getSystemServices().findSugerenciaById(id);
	    		SugerenciaVista sugerenciaVista = new SugerenciaVista(sugerencia);
	    		model.addAttribute("s", sugerenciaVista);
	 
	    		return "solicitud";
	    }
	    
	    @RequestMapping(value = "/crear", method = RequestMethod.POST)
	    public String CrearSolicitud(HttpSession session,Model model) throws BusinessException {
	    		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
	    		model.addAttribute("categorias", categorias);
	    		return "crearSolicitud";
	    }
	    
	    @RequestMapping(value = "/listaSolicitudes", method = RequestMethod.POST)
	    public String ListaSol(HttpSession session,Model model) throws BusinessException {
		    	List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
				model.addAttribute("sugerencias", sugerencias);
	    		return "listaSolicitudes";
	    }
	    
	    @RequestMapping(value = "/creacion", method = RequestMethod.POST)
	    public String CrearSolicitud(HttpSession session,Model model,@RequestParam Long cat,@RequestParam String titulo,@RequestParam String description) throws BusinessException {
		    	Citizen c = (Citizen) session.getAttribute("user");
		    	Categoria categoria = Services.getSystemServices().findCategoriaById(cat);
		    	
	    		Sugerencia sugerencia = new Sugerencia(c,titulo,description,categoria);
	    		kafkaProducer.send(Topics.CREATE_SUGGESTION, Message.setMessage(sugerencia));
	    		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
				model.addAttribute("sugerencias", sugerencias);
	    		return "listaSolicitudes";
	    }
	    
	    @RequestMapping(value = "/VotoPositivo", method = RequestMethod.POST)
	    public String VotoPositivo(HttpSession session,Model model, @RequestParam("sugerencia") Long id) throws BusinessException {
	    		votar(session, model, id, true);
	    		return "solicitud";
	    }
	    
	    @RequestMapping(value = "/VotoNegativo", method = RequestMethod.POST)
	    public String VotoNegativo(HttpSession session,Model model, @RequestParam("sugerencia") Long id) throws BusinessException {
	    		votar(session, model, id, false);
	    		return "solicitud";
	    }
	    
	    private void votar(HttpSession session, Model model, Long id, boolean flag) throws BusinessException {
	    	Citizen c = (Citizen) session.getAttribute("user");
	    	Sugerencia sugerencia = Services.getSystemServices().findSugerenciaById(id);
	    	VotoSugerencia voto = new VotoSugerencia(sugerencia,c,flag);
	    	

	    	try{
		    	Services.getCitizenServices().voteSugerencia(voto);
		    	kafkaProducer.send(Topics.VOTE_SUGGESTION, Message.setMessage(voto));
	    	}catch (Exception e) {
	    		
	    	}
	    	
	    	if(voto.getSugerencia().getVotosTotal()>=voto.getSugerencia().getCategoria().getMinimoVotos()){
				voto.getSugerencia().setEstado(SugerenciaStatus.Aceptada); //Pasar a aceptada
				Jpa.getManager().merge(voto.getSugerencia());
				new KafkaProducer().send(Topics.ACCEPT_SUGGESTION, Message.setMessage(voto.getSugerencia())); //Enviar kafka
			}

	    	SugerenciaVista sVista = new SugerenciaVista(sugerencia);
	    	model.addAttribute("s", sVista);
	    }
	    
	    @RequestMapping(value = "/Comentario", method = RequestMethod.POST)
	    public String Comentario(HttpSession session,Model model, @RequestParam("sugerencia") Long id, @RequestParam String mensaje) throws BusinessException {
	    		Citizen c = (Citizen) session.getAttribute("user");
	    		Sugerencia sugerencia = Services.getSystemServices().findSugerenciaById(id);
	    		Comentario comentario = new Comentario(c, sugerencia, mensaje);
	    		
	    		Services.getCitizenServices().addComentario(comentario);
	    		kafkaProducer.send(Topics.COMMENT_SUGGESTION, Message.setMessage(comentario));
	    		SugerenciaVista sVista = new SugerenciaVista(sugerencia);
	        	model.addAttribute("s", sVista);
	    		return "solicitud";
	    }

}
