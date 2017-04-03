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
import es.uniovi.asw.model.exception.BusinessException;
import es.uniovi.asw.producers.KafkaProducer;

@Controller
@Scope("session")
public class AdminController {
	
	@Autowired
	 private KafkaProducer kafkaProducer;
	
	@RequestMapping(value = "/crearCategoria", method = RequestMethod.POST)
    public String CrearSolicitud(HttpSession session,Model model,@RequestParam String nombre,@RequestParam String fechamin,@RequestParam String fechamax,@RequestParam String numVotos,@RequestParam String denegadas) throws BusinessException {
    		List<Categoria> categorias = Services.getSystemServices().findAllCategories();
    		model.addAttribute("categorias", categorias);
    		return "crearSolicitud";
    }
}
