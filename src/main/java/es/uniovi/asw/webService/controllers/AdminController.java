package es.uniovi.asw.webService.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			DateFormat format = new SimpleDateFormat("dd/MM/yyy");
			Date fechaMi=new Date();
			Date fechaMa=new Date();
			try {
				fechaMi = format.parse(fechamin);
				fechaMa = format.parse(fechamax);
			} catch (ParseException e) {
				e.printStackTrace();
				return "listaSolicitudesadmin";
			}
			int numV=Integer.parseInt(numVotos);
			Categoria categoria=new Categoria(nombre,fechaMi,fechaMa,numV);
			ArrayList<String> paldeneg=new ArrayList<String>();
			for(String pal:denegadas.split(";")){
				paldeneg.add(pal);
			}
			categoria.setPalabrasNoPermitidas(paldeneg);
			Services.getAdminServices().addCategoria(categoria);
			Actions.listarSugerencias(model, null);
    		return "listaSolicitudesadmin";
    }
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String CrearSolicitud(HttpSession session,Model model,@RequestParam("sugerencia") String id) throws BusinessException {
			Services.getAdminServices().deleteSugerencia(Long.parseLong(id));
			Actions.listarSugerencias(model, null);
    		return "listaSolicitudesadmin";
    }
	
	@RequestMapping(value = "/eliminarCategoria", method = RequestMethod.POST)
    public String EliminarCategoria(HttpSession session,Model model,@RequestParam("categoria")  Long  id) throws BusinessException {
			Services.getAdminServices().deleteCategoria(id);
			Actions.listarSugerencias(model, null);
    		return "listaSolicitudesadmin";
    }
	
	@RequestMapping(value = "/modificarCat", method = RequestMethod.POST)
    public String IrAModificarCat(HttpSession session,Model model,@RequestParam("categoria") Long cat) throws BusinessException {
			Categoria categor=Services.getSystemServices().findCategoriaById(cat);
			model.addAttribute("cat", categor);
			String palabras="";
			for(String pal:categor.getPalabrasNoPermitidas()){
				palabras+=pal+";";
			}
			model.addAttribute("palabrasNoPermitidas", palabras);
			DateFormat format = new SimpleDateFormat("dd/MM/yyy");
			String fin = format.format(categor.getFechaFin());
			String inicio = format.format(categor.getFechaInicio());
			model.addAttribute("fechaInicio", inicio);
			model.addAttribute("fechaFin", fin);
			
    		return "editarCategoria";
    }
	
	@RequestMapping(value = "/modificarCategoria", method = RequestMethod.POST)
    public String ModificarCategoria(HttpSession session,Model model,@RequestParam String nombre,@RequestParam String fechamin,@RequestParam String fechamax,@RequestParam String numVotos,@RequestParam String denegadas) throws BusinessException {
			DateFormat format = new SimpleDateFormat("dd/MM/yyy");
			Date fechaMi=new Date();
			Date fechaMa=new Date();
			try {
				fechaMi = format.parse(fechamin);
				fechaMa = format.parse(fechamax);
			} catch (ParseException e) {
				e.printStackTrace();
				return "listaSolicitudesadmin";
			}
			int numV=Integer.parseInt(numVotos);
			Categoria categoria=new Categoria(nombre,fechaMi,fechaMa,numV);
			ArrayList<String> paldeneg=new ArrayList<String>();
			for(String pal:denegadas.split(";")){
				paldeneg.add(pal);
			}
			categoria.setPalabrasNoPermitidas(paldeneg);
			Services.getAdminServices().updateCategoria(categoria);
			Actions.listarSugerencias(model, null);
    		return "listaSolicitudesadmin";
    }
	
}
