package es.uniovi.asw.webService;


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
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.producers.KafkaProducer;

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
    	
    	if(userValidator.validate(username, password,"citi")){
    		session.setAttribute("user", new User(username,password));
    		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
    		model.addAttribute("sugerencias", sugerencias); //Ordenar en un diccionario por categoria / ¿una extra para las propias del usuario? 
    		return "listaSolicitudes";
    	}
    	
    	else if(userValidator.validate(username, password,"admin")){
    		session.setAttribute("user", new User(username,password));
    		return "admin";
    	}

    	return "login";	
    }
    @RequestMapping(value = "/ver", method = RequestMethod.POST)
    public String AbrirSolicitud(HttpSession session,Model model,@RequestParam String ver) {
    		//Ver que sea la ID de la sugerencia
    		//GetSugerencia(ver)
    		model.addAttribute("sol", ver);
    		return "solicitud";
    }
    
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String CrearSolicitud(HttpSession session,Model model,@RequestParam String ver) {
    	
    		return "crearSolicitud";
    }
    
    @RequestMapping(value = "/listaSolicitudes", method = RequestMethod.POST)
    public String ListaSol(HttpSession session,Model model) throws BusinessException {
	    	List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
			model.addAttribute("sugerencias", sugerencias);
    		return "listaSolicitudes";
    }
    
    @RequestMapping(value = "/creacion", method = RequestMethod.POST)
    public String CrearSolicitud(HttpSession session,Model model,@RequestParam String cat,@RequestParam String titulo,@RequestParam String description) throws BusinessException {
	    	//Add(categoria,titulo,descripcion,usuario);
    		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
			model.addAttribute("sugerencias", sugerencias);
    		return "listaSolicitudes";
    }
    
}