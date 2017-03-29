package es.uniovi.asw.webService;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.producers.KafkaProducer;

@Controller
@Scope("session")
public class MainController {
	
	 @Autowired
	 private KafkaProducer kafkaProducer;
	 
    @RequestMapping("/")
    public String landing(Model model) {
    	model.addAttribute("message", new Message());
        return "index";
    }
    
    @RequestMapping("/send")
    public String send(Model model, @ModelAttribute Message message) {
        kafkaProducer.send("exampleTopic", message.getMessage());
        return "redirect:/";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session,Model model,@RequestParam String username, @RequestParam String password) {
    	
    	if(userValidator.validate(username, password,"citi")){
    		session.setAttribute("user", new User(username,password));
    		//session.setAttribute("solicitudes", new Solicitudes(username));
    		return "listaSolicitudes";
    	}
    	
    	else if(userValidator.validate(username, password,"admin")){
    		session.setAttribute("user", new User(username,password));
    		return "admin";
    	}

    		return "login";	
    }
    @RequestMapping(value = "/listaSolicitudes", method = RequestMethod.POST)
    public String AbrirSolicitud(HttpSession session,Model model,@RequestParam String solicitud) {
    	
    		//session.setAttribute("solucion", new Solucion(solucion));
    		return "solicitud";
    }
}