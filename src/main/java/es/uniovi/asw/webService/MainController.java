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
import es.uniovi.asw.model.Administrador;
import es.uniovi.asw.model.Categoria;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Sugerencia;
import es.uniovi.asw.model.VotoSugerencia;
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
    		Citizen c = Services.getSystemServices().findCitizenByUserAndPass("Seila_seila", "llFh9oTmjUI=");
    		session.setAttribute("user", c);
    		List<Sugerencia> sugerencias = Services.getSystemServices().findAllSugerencias();
    		model.addAttribute("sugerencias", sugerencias);
    		
    		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
    		
    		for (Categoria categoria: categorias) {
    			Long idCategoria = categoria.getId();
    			sugerencias = Services.getSystemServices().findSugerenciasByCategory(idCategoria);
    			model.addAttribute("sugerencias"+idCategoria, sugerencias);
    		}
    		 //Ordenar en un diccionario por categoria / ¿una extra para las propias del usuario? 
    		return "listaSolicitudes";
    	}
    	
    	else if(userValidator.validate(username, password,"admin")){
    		Administrador admin = Services.getSystemServices().findAdminByUserAndPass("admin", "admin");
    		session.setAttribute("admin", admin);
    		return "listaSolicitudesadmin";
    	}

    	return "login";	
    }
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
    		Services.getCitizenServices().addSugerencia(sugerencia);
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
    	Services.getCitizenServices().voteSugerencia(voto);
    	
    	SugerenciaVista sVista = new SugerenciaVista(sugerencia);
    	model.addAttribute("s", sVista);
    }
    
    @RequestMapping(value = "/Comentario", method = RequestMethod.POST)
    public String Comentario(HttpSession session,Model model, @RequestParam("sugerencia") Long id, @RequestParam String mensaje) throws BusinessException {
    		Citizen c = (Citizen) session.getAttribute("user");
    		Sugerencia sugerencia = Services.getSystemServices().findSugerenciaById(id);
    		Comentario comentario = new Comentario(c, sugerencia, mensaje);
    		
    		Services.getCitizenServices().addComentario(comentario);
    		SugerenciaVista sVista = new SugerenciaVista(sugerencia);
        	model.addAttribute("s", sVista);
    		return "solicitud";
    }
    
}