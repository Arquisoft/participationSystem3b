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
import es.uniovi.asw.model.Administrador;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoSugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.producers.KafkaProducer;
import es.uniovi.asw.webService.SugerenciaVista;
import es.uniovi.asw.webService.userValidator;

@Controller
@Scope("session")
public class MainController {
	
	 @Autowired
	 private KafkaProducer kafkaProducer;
	 
    @RequestMapping("/")
    public String landing(Model model) {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session,Model model,@RequestParam String username, @RequestParam String password) throws BusinessException {
    	
    	List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
		model.addAttribute("sugerencias", sugerencias);
		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
		model.addAttribute("categorias", categorias);
		
    	if(userValidator.validate(username, password,"citi")){
    		Citizen c = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
    		session.setAttribute("user", c);
    		session.setAttribute("admin", null);
    		
    		Actions.listarCategorias(model, c);
    		return "listaSolicitudes";
    	}
    	
    	else if(userValidator.validate(username, password,"admin")){
    		Administrador admin = Services.getSystemServices().findAdminByUserAndPass("admin", "admin");
    		session.setAttribute("admin", admin);
    		session.setAttribute("user", null);
    		return "listaSolicitudesadmin";
    	}

    	return "login";	
    }
    
    @RequestMapping(value = "/listaSolicitudes", method = RequestMethod.POST)
    public String ListaSol(HttpSession session,Model model) throws BusinessException {
	    	List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
			model.addAttribute("sugerencias", sugerencias);
			
			if (session.getAttribute("user") != null) {
				Citizen c = (Citizen) session.getAttribute("user");
				Actions.listarCategorias(model, c);
				return "listaSolicitudes";
			} else 
				return "listaSolicitudesadmin";
    }
    
}